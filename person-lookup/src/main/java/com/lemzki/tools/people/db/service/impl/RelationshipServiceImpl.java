package com.lemzki.tools.people.db.service.impl;

import com.lemzki.tools.people.db.exception.RelationShipPersistenceException;
import com.lemzki.tools.people.db.model.PersonDb;
import com.lemzki.tools.people.db.model.Relationship;
import com.lemzki.tools.people.db.repository.RelationshipRepository;
import com.lemzki.tools.people.db.service.RelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class RelationshipServiceImpl implements RelationshipService {

    @Autowired
    RelationshipRepository relationshipRepository;

    //find relationships where the personDb is the main
    @Override
    public Set<Relationship> findRelationsips(PersonDb personDb) {
        return relationshipRepository.findByMain(personDb);
    }

    public void saveOrUpdate(Relationship relationship) {
        //this assumes that persons in the family (parent and children) and relationstypes are already in db
        //if not present, a hibernate exception is to be expected
        try {
            PersonDb mainPersonDb = relationship.getMain();
            Optional<Relationship> relationshipOptional = relationshipRepository.findByMainAndOther(relationship.getMain(), relationship.getOther());

            if (relationshipOptional.isPresent()) {
                //record is alreadyin db just need to update it
                Relationship relationshipEntity = relationshipOptional.get();
                relationshipEntity.getRelation().addAll(relationship.getRelation());
                relationshipRepository.save(relationshipEntity);
            } else {
                //no record of this family yet
                relationshipRepository.save(relationship);
            }
        } catch (Exception e) {
            throw new RelationShipPersistenceException("Issue with saving/updating relationship. Some entities may not have been saved." + e.getMessage());
        }
    }

    @Override
    public void saveOrUpdate(Set<Relationship> relationships) {
        relationships.forEach(this::saveOrUpdate);
    }
}
