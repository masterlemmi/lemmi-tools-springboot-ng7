package com.lemzki.tools.charts;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
@ToString

/**
 * Chart properties based from NGX Charting properties
 */
public class ChartMultiValue  {
    private String name;
    private List<ChartValue> series;

    public ChartMultiValue(String name, List<ChartValue> series){
        this.name= name;
        this.series = series;
    }




    public <K, V> ChartMultiValue(String name, Map<K, V> map) {
        this(name, mapToSeries(map));
    }

    private static <K, V> List<ChartValue> mapToSeries(Map<K,V> map) {
        return map.entrySet()
                .stream()
                .map(entry->new ChartValue(entry.getKey().toString(), entry.getValue().toString()))
                .collect(toList());
    }


}
