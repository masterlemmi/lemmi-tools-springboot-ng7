package com.lemzki.tools.security.util;

import java.util.function.Predicate;

public class StreamUtils {

    public static <T> Predicate<T> not(Predicate<T> t) {
        return t.negate();
    }


}
