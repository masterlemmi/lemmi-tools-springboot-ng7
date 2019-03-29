package com.lemzki.tools.charts;

import lombok.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
@ToString

/**
 * Chart properties based from NGX Charting properties
 */
public class ChartValue<N,V> {
    private N name;
    private V value;

    public ChartValue(N name, V value){
        this.name = name;
        this.value = value;
    }


}
