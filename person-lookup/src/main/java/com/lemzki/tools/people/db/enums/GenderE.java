package com.lemzki.tools.people.db.enums;

import com.lemzki.tools.people.db.exception.GenderUndeterminedException;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.function.Predicate;

public enum GenderE {
    MALE("M"), FEMALE("F"), UNDETERMINED("-");

    String abbr;

    GenderE(String abbr){
        this.abbr = abbr;
    }

    public static GenderE getEnum(String gender) {

        return Arrays.stream(values()).filter(determine(gender)).findAny().orElseThrow(GenderUndeterminedException::new);
    }

    //no throwing of errors
    public static GenderE getSafeEnum(String gender) {

        return Arrays.stream(values()).filter(determine(gender)).findAny().orElse(UNDETERMINED);
    }

    private static Predicate<GenderE> determine(String gender) {
        return genderEnum -> !StringUtils.isEmpty(gender) && gender.toUpperCase().startsWith(genderEnum.abbr);
    }

    public String getAbbreviation(){
        return this.abbr;
    }

}