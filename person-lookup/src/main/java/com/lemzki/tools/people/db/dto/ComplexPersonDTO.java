package com.lemzki.tools.people.db.dto;

import com.lemzki.tools.people.db.enums.Gender;
import lombok.*;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true, exclude = {"parents", "children", "siblings"})
public class ComplexPersonDTO extends PersonDTO implements FamilyOriented {
    private String nickname;
    private boolean deceased;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;
    private int age;
    private Set<PersonDTO> parents = new HashSet<>();
    private List<PersonDTO> children = new ArrayList<>();
    private List<PersonDTO> siblings = new ArrayList<>();
    private Map<String, Set<PersonDTO>> relationships = new HashMap<>(); //e.g. bestfriends={a,b,c,d}, godfather={d,e,f,f}

    public ComplexPersonDTO (PersonDTO simplePerson){
        super.setId(simplePerson.getId());
        super.setName(simplePerson.getName());
        super.setPhotoUrl(simplePerson.getPhotoUrl());
        super.setGender(simplePerson.getGender());
    }

}
