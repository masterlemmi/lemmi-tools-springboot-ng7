package com.lemzki.tools.people.db.mapper.impl;


import com.google.common.collect.Sets;
import com.lemzki.tools.people.db.dto.PersonDTO;
import com.lemzki.tools.people.db.model.Person;
import com.lemzki.tools.people.db.model.RelationType;
import com.lemzki.tools.people.db.model.Relationship;
import org.apache.commons.collections4.MapUtils;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RelationshipMapper {

    public static Map<String, Set<PersonDTO>> mapModel(Set<Relationship> relationships) {
        Map<String, Set<PersonDTO>> map = new HashMap<>();

        if (CollectionUtils.isEmpty(relationships))
            return map;

        for (Relationship relationship : relationships) {
            Set<RelationType> relationTypes = relationship.getRelation();
            PersonDTO other = PersonMapper.toSimpleResource(relationship.getOther());

            for (RelationType relationType : relationTypes) {
                String key = relationType.getName();

                if (map.containsKey(key)) {
                    map.get(key).add(other);
                } else {
                    map.put(key, Sets.newHashSet(other));
                }
            }
        }

        return map;
    }

    public static Map<Person, Set<RelationType>>  mapModel(Map<String, Set<PersonDTO>> relationsips) {
        Map<Person, Set<RelationType>> results = new HashMap<>();


        if (MapUtils.isEmpty(relationsips)) {
            return results;
        }

        Map<Person, Set<RelationType>> personToRelationn = new HashMap<>();


        for (Map.Entry<String, Set<PersonDTO>> entry : relationsips.entrySet()) {
            RelationType relationType = new RelationType(entry.getKey());

            for (PersonDTO pDTO : entry.getValue()) {
                Person person = PersonMapper.mapResource(pDTO);

                if(personToRelationn.containsKey(person)){
                    personToRelationn.get(person).add(relationType);
                } else {
                    personToRelationn.put(person, Sets.newHashSet(relationType));
                }
            }
        }



        return results;

    }
}
