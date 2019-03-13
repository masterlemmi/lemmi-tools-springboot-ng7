package com.lemzki.tools.interests.finance.debts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

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



}
