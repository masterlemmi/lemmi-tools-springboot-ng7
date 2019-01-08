package com.lemzki.tools.people.db.controller.impl;

import com.lemzki.tools.people.db.controller.PersonController;
import com.lemzki.tools.people.db.dto.ComplexPersonDTO;
import com.lemzki.tools.people.db.dto.PersonDTO;
import com.lemzki.tools.people.db.dto.builder.ResourceBuilder;
import com.lemzki.tools.people.db.mapper.impl.PersonMapper;
import com.lemzki.tools.people.db.model.Person;
import com.lemzki.tools.people.db.exception.PersonNotFoundException;
import com.lemzki.tools.people.db.service.*;
import com.lemzki.tools.people.db.util.RecentHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static java.util.stream.Collectors.toList;

@RestController
public class PersonControllerImpl implements PersonController {
    @Autowired
    PersonService personService;

    @Autowired
    RelationshipService relationshipService;

    @Autowired
    FamilyService familyService;

    @Autowired
    RelationTypeService relationTypeService;

    @Autowired
    ComplexPersonService complexPersonService;

    @Autowired
    RecentHolder<PersonDTO> recents;


    @Override
    public String home() {
        return "welcome to family -db";
    }



    @Override
    public List<PersonDTO> getAllPeople() {
        return personService.findAll()
                .stream()
                .map(this::createPersonResource)
                .collect(toList());
    }

    @Override
    public List<PersonDTO> getRecentPeople() {
        return recents.getAll();
    }


    @Override
    public PersonDTO getPerson(long id) {
        PersonDTO personDTO =  personService.find(id)
                .map(this::createPersonResource)
                .orElseThrow(PersonNotFoundException::new);
        recents.add(personDTO);

        return personDTO;
    }

    private boolean notYetInList (List<Long> foundIds, long id){

        return !foundIds.contains(id);
    }

    @Override
    public List<PersonDTO> findByNameorNickName(String nickNameOrName, Optional<List<Long>> excludeList) {
        return personService.findByNameOrNickName(nickNameOrName).stream()
                .filter(x-> !excludeList.isPresent() || notYetInList(excludeList.get(), x.getId()))
                .map(this::createPersonResource)
                .collect(toList());
    }

    @Override
    public PersonDTO addPerson(ComplexPersonDTO personResource) {

        return complexPersonService.add(personResource);

    }


    //only update the actual person and not other relationships/family/etc.. those can be updated via separate controller
    @Override
    public PersonDTO updatePerson(long id, PersonDTO personDTO) {
        checkIfPersonInDB(id);

        Person person = personService.save(PersonMapper.mapResource(personDTO));

        return PersonMapper.toResource(person);
    }

    @Override
    public void deletePerson(long id) {

        checkIfPersonInDB(id);

        personService.delete(id);
    }

    private void checkIfPersonInDB(long id) {
        Optional<Person> personOptional = personService.find(id);

        if (!personOptional.isPresent()) {
            throw new PersonNotFoundException();
        }
    }


    private PersonDTO createPersonResource(Person person) {
        return ResourceBuilder.createPersonResource(person)
                .addChildren(familyService::findChildren)
                .addSiblings(familyService::findSiblings)
                .addParents(familyService::findParents)
                .addRelationships(relationshipService::findRelationsips)
                .build();
    }
}
