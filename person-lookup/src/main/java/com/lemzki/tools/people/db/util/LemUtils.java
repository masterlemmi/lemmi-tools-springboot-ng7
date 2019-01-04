package com.lemzki.tools.people.db.util;

import java.util.function.Predicate;

public class LemUtils {

    public static <T> Predicate<T> not(Predicate<T> t) {
        return t.negate();
    }
}
