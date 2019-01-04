package com.lemzki.tools.people.db.dto;


import com.lemzki.tools.people.db.model.RelationType;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = "persons")
@NoArgsConstructor
@AllArgsConstructor
public class RelationshipDTO {
    private String relation;
    private Set<PersonDTO> persons;

    public RelationshipDTO(String relation){
        this(relation, new HashSet<>());
    }
}
