package com.lemzki.tools.people.db.service.impl;

import com.google.common.collect.Sets;
import com.lemzki.tools.people.db.exception.FamilyPersistenceException;
import com.lemzki.tools.people.db.model.Family;
import com.lemzki.tools.people.db.model.PersonDb;
import com.lemzki.tools.people.db.repository.FamilyRepository;
import com.lemzki.tools.people.db.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.lemzki.tools.util.StreamUtils.*;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Service
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    FamilyRepository familyRepository;

    @Override
    public void saveOrUpdate(Set<Family> superFamily) {
        superFamily.forEach(this::saveOrUpdate);
    }
    @Override
    public void saveOrUpdate(Family family) {

        //this assumes that persons in the family (parent and children) are already in db
        //if not present, a hibernate exception is to be expected

        try {
            Optional<Family> optFamily = familyRepository.findByParent(family.getParent());

            if (optFamily.isPresent()) {
                //record is alreadyin db just need to update it
                Family presentFamily = optFamily.get();
                presentFamily.getChildren().addAll(family.getChildren());
                familyRepository.save(presentFamily);
            } else {
                //no record of this family yet
                familyRepository.save(family);
            }
        } catch (Exception e) {
            throw new FamilyPersistenceException("Issue with saving/updating family. Some entities may not have been saved." + e.getMessage());
        }
    }

    @Override
    public List<PersonDb> findChildren(PersonDb parent) {
        Set<PersonDb> children = familyRepository.findByParent(parent)
                .map(Family::getChildren)
                .orElse(Sets.newHashSet());

        return  sortedByBirthDate(children);
    }

    @Override
    public List<PersonDb> findSiblings(PersonDb personDb) {
        Set<PersonDb> siblings = familyRepository.findParents(personDb)
                .stream()
                .map(Family::getChildren)
                .flatMap(Set::stream)
                .filter(not(p -> p.equals(personDb)))
                .collect(toSet());

        return sortedByBirthDate(siblings);
    }

    @Override
    public Set<PersonDb> findParents(PersonDb personDb) {
        return familyRepository.findParents(personDb)
                .stream()
                .map(Family::getParent)
                .collect(toSet());
    }

    private List<PersonDb> sortedByBirthDate(Collection<PersonDb> people){
        return new ArrayList<>(people).stream()
                .sorted(comparing(PersonDb::getDateOfBirth))
                .collect(toList());
    }



}
