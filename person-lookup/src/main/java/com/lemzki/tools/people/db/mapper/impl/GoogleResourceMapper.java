package com.lemzki.tools.people.db.mapper.impl;

import com.google.api.services.people.v1.model.*;
import com.lemzki.tools.people.db.enums.Gender;
import com.lemzki.tools.people.db.exception.GenderRequiredException;
import com.lemzki.tools.people.db.mapper.Result;
import com.lemzki.tools.people.db.model.PersonDb;
import com.lemzki.tools.people.db.validator.GenderValidator;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;

public class GoogleResourceMapper {
    public static Result map(com.google.api.services.people.v1.model.Person gPerson) {
        Result result ;
        PersonDb personDb = new PersonDb();
        try {
            personDb.setResourceName(gPerson.getResourceName());
            personDb.setName(determineNames(gPerson.getNames()));
            personDb.setGender(determineGender(gPerson.getGenders(), gPerson.getRelations()));
            personDb.setDateOfBirth(determineDateOfBirth(gPerson.getBirthdays()));
            personDb.setNickname(determineNicknames(gPerson.getNicknames()));
            personDb.setDateOfDeath(determineDateOfDeath(gPerson.getUserDefined()));
            personDb.setPhotoUrl(determinePhotoUrl(gPerson.getPhotos()));
            result = new Result.Success(personDb);
        } catch (Exception e){
            result = new Result.Fail(personDb, e.getMessage());
        }

        return result;
    }

    private static String determinePhotoUrl(List<Photo> photos) {
        return null;
    }

    private static LocalDate determineDateOfDeath(List<UserDefined> userDefined) {
        return null;
    }

    private static String determineNicknames(List<Nickname> nicknames) {
        return null;
    }

    private static String determineNames(List<Name> names) {
        return "";
    }

    //There is no gender field in the mobile app so using Relationship Custom Label as placeholder for gender
    private static Gender determineGender(List<com.google.api.services.people.v1.model.Gender> genders,
                                          List<Relation> relations) {


        if(CollectionUtils.isEmpty(genders) && CollectionUtils.isEmpty(relations)) throw new GenderRequiredException();

        //check genders first because that would have been set manually somewhere
       for (com.google.api.services.people.v1.model.Gender gender: genders){
           String value = gender.getValue(); //female or male
       }











        return null;
    }




    private static LocalDate determineDateOfBirth(List<Birthday> birthday){

        return null;
    }



}
