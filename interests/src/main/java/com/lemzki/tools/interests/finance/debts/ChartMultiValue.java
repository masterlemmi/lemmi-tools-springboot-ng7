package com.lemzki.tools.interests.finance.debts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
@ToString
@AllArgsConstructor
/**
 * Chart properties based from NGX Charting properties
 */
public class ChartMultiValue  {
    private String name;
    private List<ChartValue> series;


    public ChartMultiValue(Chartable chartable){
        this(chartable.getName(),chartable.getSeries());
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
