package com.lemzki.tools.people.db.service.impl;

import com.lemzki.tools.people.db.dto.ComplexPersonDTO;
import com.lemzki.tools.people.db.dto.PersonDTO;
import com.lemzki.tools.people.db.mapper.impl.PersonMapper;
import com.lemzki.tools.people.db.mapper.impl.RelationshipMapper;
import com.lemzki.tools.people.db.model.Family;
import com.lemzki.tools.people.db.model.Person;
import com.lemzki.tools.people.db.model.RelationType;
import com.lemzki.tools.people.db.model.Relationship;
import com.lemzki.tools.people.db.service.*;
import com.lemzki.tools.people.db.util.LemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.lemzki.tools.people.db.util.LemUtils.*;
import static java.util.stream.Collectors.toSet;

@Service
public class ComplexPersonServiceImpl implements ComplexPersonService {

    @Autowired
    RelationTypeService relationTypeService;

    @Autowired
    PersonService personService;

    @Autowired
    RelationshipService relationshipService;


    @Autowired
    FamilyService familyService;

    @Override
    public ComplexPersonDTO add(ComplexPersonDTO personResource) {
        //we need save here and not retrieve if existing because we assume this to be a new person not yet in db
        Person person = personService.save(PersonMapper.mapResource(personResource));

        if(CollectionUtils.isEmpty(personResource.getParents())){
            saveParents(person, personResource.getParents());
        }

        if(CollectionUtils.isEmpty(personResource.getChildren())){
            saveChildren(person, personResource.getChildren());
        }

        if(CollectionUtils.isEmpty(personResource.getRelationships())){
            saveRelationships(person, personResource.getRelationships());
        }

        personResource.setId(person.getId());
        return personResource;
    }

    private void saveChildren(Person person, List<PersonDTO> childrenSet) {
        Set<Person> children = childrenSet.stream()
                //removeperson from list in case it was added
                .filter(x->!x.getId().equals(person.getId()))
                .map(PersonMapper::mapResource)
                .map(personService::retrieveOrSave)
                .collect(toSet());

        Family directFamily = new Family(person, children);
        familyService.saveOrUpdate(directFamily);
    }

    private void saveParents(Person person, Set<PersonDTO> parentsSet) {
        Set<Person> parents = parentsSet.stream()
                //removeperson from list in case it was added
                .filter(x->!x.getId().equals(person.getId()))
                .map(PersonMapper::mapResource)
                .map(personService::retrieveOrSave)
                .collect(toSet());


        Set<Family> superFamily = parents.stream()
                .map(parent -> {
                    Family family = new Family();
                    family.getChildren().add(person);
                    family.setParent(parent);
                    return family;
                }).collect(toSet());

        familyService.saveOrUpdate(superFamily);
    }


    private void saveRelationships(Person person, Map<String, Set<PersonDTO>> relationshipsMap) {
        Map<Person, Set<RelationType>> personRelationMap = RelationshipMapper.mapModel(relationshipsMap);

        Set<Relationship> relationships = personRelationMap.entrySet().stream()
                .map(entrySet -> {
                    Set<RelationType> relationTypes =  entrySet.getValue().stream()
                            .map(relationTypeService::retrieveOrSave)
                            .collect(toSet());
                    Person theOther = personService.retrieveOrSave(entrySet.getKey());

                    Relationship rel = new Relationship();
                    rel.setMain(person);
                    rel.setOther(theOther);
                    rel.setRelation(relationTypes);
                    return rel;
                })
                .collect(toSet());

        relationshipService.saveOrUpdate(relationships);
    }
}
