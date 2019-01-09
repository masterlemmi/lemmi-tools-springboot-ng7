package com.lemzki.tools.util;

import java.util.HashSet;
import java.util.List;

public class ListUtils {

    public static <T> boolean containsUnique(List<T> list){
        return list.stream().allMatch(new HashSet<>()::add);
    }
}
