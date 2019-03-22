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
public class ChartValue {
    private String name;
    private String value;

    public ChartValue(String name, String value){
        this.name = name;
        this.value = value;
    }


}
