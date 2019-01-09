package com.lemzki.tools.util;

import java.util.function.Function;
import java.util.function.Predicate;

public class StreamUtils {

    public static <T> Predicate<T> not(Predicate<T> t) {
        return t.negate();
    }

    public static <T> Predicate<T> sameAs (Function<T, String> fn, String matcher) {
        return  t -> fn.apply(t).equalsIgnoreCase(matcher);
    }

}
