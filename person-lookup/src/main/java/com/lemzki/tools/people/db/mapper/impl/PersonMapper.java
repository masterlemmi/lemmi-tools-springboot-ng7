package com.lemzki.tools.people.db.mapper.impl;

import com.lemzki.tools.people.db.dto.ComplexPersonDTO;
import com.lemzki.tools.people.db.dto.PersonDTO;
import com.lemzki.tools.people.db.model.PersonDb;

import java.time.LocalDate;
import java.time.Period;

public class PersonMapper {

    //only maps the simple parts of the person not the relationships/parents etc
    public static PersonDb mapResource(PersonDTO personDTO) {
        ComplexPersonDTO dto = (ComplexPersonDTO) personDTO;
        PersonDb personDb = new PersonDb();
        personDb.setId(dto.getId());
        personDb.setGender(dto.getGender());
        personDb.setNickname(dto.getNickname());
       // personDb.setName(dto.getName());
        personDb.setDateOfBirth(dto.getDateOfBirth());
        return personDb;
    }

    public static PersonDTO toSimpleResource(PersonDb personDb) {
        PersonDTO simple = new PersonDTO();
        simple.setPhotoUrl(personDb.getPhotoUrl());
        simple.setId(personDb.getId());
        simple.setGender(personDb.getGender());
        simple.setName(personDb.getName());
        return simple;
    }

    public static PersonDTO toResource(PersonDb personDb) {
        ComplexPersonDTO complexPersonDTO = new ComplexPersonDTO(toSimpleResource(personDb));
        complexPersonDTO.setNickname(personDb.getNickname());
        complexPersonDTO.setDateOfBirth(personDb.getDateOfBirth());
        complexPersonDTO.setDateOfDeath(personDb.getDateOfDeath());
        complexPersonDTO.setDeceased(personDb.getDateOfDeath() !=null);
        complexPersonDTO.setAge(complexPersonDTO.isDeceased() ? calculateDeathAge (personDb): calculateAge(personDb));
        return complexPersonDTO;
    }

    private static int calculateDeathAge(PersonDb personDb) {
        return personDb.getDateOfBirth() == null ? 0 : Period.between(personDb.getDateOfBirth(), personDb.getDateOfDeath()).getYears();
    }

    private static int calculateAge(PersonDb personDb) {
        return personDb.getDateOfBirth() == null ? 0 : Period.between(personDb.getDateOfBirth(), LocalDate.now()).getYears();
    }

}
