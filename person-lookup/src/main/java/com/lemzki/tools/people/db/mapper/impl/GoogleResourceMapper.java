package com.lemzki.tools.people.db.mapper.impl;

import com.google.api.services.people.v1.model.*;
import com.jasongoodwin.monads.Try;
import com.lemzki.tools.people.db.enums.GenderE;
import com.lemzki.tools.people.db.mapper.Result;
import com.lemzki.tools.people.db.model.PersonDb;
import com.lemzki.tools.people.db.validator.GenderExtractor;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import static com.lemzki.tools.util.StreamUtils.sameAs;

public class GoogleResourceMapper {

    private static final String RANDOM_PHOTO = "https://source.unsplash.com/random/300x300";
    private static final int RANDOM_YEAR = 1986;

    public static Result map(com.google.api.services.people.v1.model.Person gPerson) {
        Result result;
        PersonDb personDb = new PersonDb();
        try {
            personDb.setResourceName(gPerson.getResourceName());
            personDb.setFirstName(determineNames(gPerson.getNames(), Name::getGivenName));
            personDb.setAdditionalName(determineNames(gPerson.getNames(), Name::getGivenName));
            personDb.setLastName(determineNames(gPerson.getNames(), Name::getFamilyName));
            personDb.setGender(determineGender(gPerson.getGenders(), gPerson.getRelations()));
            personDb.setDateOfBirth(determineDateOfBirth(gPerson.getBirthdays()));
            personDb.setNickname(determineNicknames(gPerson.getNicknames()));
            personDb.setDateOfDeath(determineDateOfDeath(gPerson.getEvents()));
            personDb.setPhotoUrl(determinePhotoUrl(gPerson.getPhotos()));
            result = new Result.Success(personDb);
        } catch (Exception e) {
            result = new Result.Fail(personDb, e.getMessage());
            e.printStackTrace();
        }

        return result;
    }


    private static String determineNames(List<Name> names, Function<Name, String> getName) {
        if(CollectionUtils.isEmpty(names)) return "Unknown";
        return names.stream()
                .map(getName::apply)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse("Unknown");
    }

    //There is no gender field in the contacts mobile app so using Relationship Custom Label as placeholder for gender
    private static GenderE determineGender(List<com.google.api.services.people.v1.model.Gender> genders,
                                           List<Relation> relations) {

        if (CollectionUtils.isEmpty(relations)) {
            return GenderE.UNDETERMINED;
            //throw new GenderRequiredException();
        }

        GenderExtractor extractor = new GenderExtractor.Builder().from(relations).build();

        return extractor.extractGender();
    }

    private static String determinePhotoUrl(List<Photo> photos) {
        if(CollectionUtils.isEmpty(photos)) return RANDOM_PHOTO;
        return photos.stream()
                .map(Photo::getUrl)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(RANDOM_PHOTO);
    }

    private static String determineNicknames(List<Nickname> nicknames) {
        if(CollectionUtils.isEmpty(nicknames)) return "";
        return nicknames.stream()
                .map(Nickname::getValue)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse("");
    }

    private static LocalDate determineDateOfDeath(List<Event> events) {
        if(CollectionUtils.isEmpty(events)) return null;
        return events.stream()
                .filter(sameAs(Event::getType, "Death"))
                .filter(Objects::nonNull)
                .findFirst()
                .map(Event::getDate)
                .map(toLocalDate)
                .orElse(null);
    }


    private static LocalDate determineDateOfBirth(List<Birthday> birthdays) {
        if(CollectionUtils.isEmpty(birthdays)) return null;
        return birthdays.stream()
                .filter(Objects::nonNull)
                .findFirst()
                .map(Birthday::getDate)
                .map(toLocalDate)
                .orElse(null);
    }

    private static Function<Date,LocalDate> toLocalDate = d ->
         Try.ofFailable(()->
                 LocalDate.of(
                     d.getYear() == null? RANDOM_YEAR: d.getYear(),
                         d.getMonth(),
                         d.getDay()
                 )
         ).orElse(null);

}
