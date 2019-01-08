package com.lemzki.tools.people.db.mapper.impl;

import com.lemzki.tools.people.db.dto.ComplexPersonDTO;
import com.lemzki.tools.people.db.dto.PersonDTO;
import com.lemzki.tools.people.db.model.Person;

import java.time.LocalDate;
import java.time.Period;

public class PersonMapper {

    //only maps the simple parts of the person not the relationships/parents etc
    public static Person mapResource(PersonDTO personDTO) {
        ComplexPersonDTO dto = (ComplexPersonDTO) personDTO;
        Person person = new Person();
        person.setId(dto.getId());
        person.setGender(dto.getGender());
        person.setNickname(dto.getNickname());
        person.setName(dto.getName());
        person.setDateOfBirth(dto.getDateOfBirth());
        return person;
    }

    public static PersonDTO toSimpleResource(Person person) {
        PersonDTO simple = new PersonDTO();
        simple.setPhotoUrl(person.getPhotoUrl());
        simple.setId(person.getId());
        simple.setGender(person.getGender());
        simple.setName(person.getName());
        return simple;
    }

    public static PersonDTO toResource(Person person) {
        ComplexPersonDTO complexPersonDTO = new ComplexPersonDTO(toSimpleResource(person));
        complexPersonDTO.setNickname(person.getNickname());
        complexPersonDTO.setDateOfBirth(person.getDateOfBirth());
        complexPersonDTO.setDateOfDeath(person.getDateOfDeath());
        complexPersonDTO.setDeceased(person.getDateOfDeath() !=null);
        complexPersonDTO.setAge(complexPersonDTO.isDeceased() ? calculateDeathAge (person): calculateAge(person));
        return complexPersonDTO;
    }

    public static Person mapGoogleResource(com.google.api.services.people.v1.model.Person gPerson) {
        Person person = new Person();
//        person.setDateOfBirth(determineDateOfBirth(gPerson.getBirthdays()));
//        person.setGender(determineGender(gPerson.getGenders()));
//        person.setName(determineNames(gPerson.getNames()));
//        person.setNickname(determineNicknames(gPerson.getNicknames()));
//        person.setDateOfDeath(determineDateOfDeath(gPerson.getUserDefined()));
//        person.setPhotoUrl(determinePhotoUrl(gPerson.getPhotos()));
//        person.setAddedBy();
//
//
//    }
//
//    private static Gender determineGender(List<com.google.api.services.people.v1.model.Gender> genders) {
//    }
//
//
//
//
//    private static LocalDate determineDateOfBirth(List<Birthday> birthday){
//
//    }
        return null;
    }


    private static int calculateDeathAge(Person person) {
        return person.getDateOfBirth() == null ? 0 : Period.between(person.getDateOfBirth(), person.getDateOfDeath()).getYears();
    }

    private static int calculateAge(Person person) {
        return person.getDateOfBirth() == null ? 0 : Period.between(person.getDateOfBirth(), LocalDate.now()).getYears();
    }

}
