package com.lemzki.tools.people.db.enums;

import com.lemzki.tools.people.db.exception.GenderRequiredException;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.function.Predicate;

public enum Gender {
    MALE("M"), FEMALE("F"), UNSPECIFIED("-");

    String abbr;

    Gender(String abbr){
        this.abbr = abbr;
    }

    public static Gender getEnum(String gender) {

        return Arrays.stream(values()).filter(determine(gender)).findAny().orElseThrow(GenderRequiredException::new);
    }

    private static Predicate<Gender> determine(String gender) {
        return genderEnum -> !StringUtils.isEmpty(gender) && gender.toUpperCase().startsWith(genderEnum.abbr);
    }

    public String getAbbreviation(){
        return this.abbr;
    }

}