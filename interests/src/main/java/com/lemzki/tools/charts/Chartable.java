package com.lemzki.tools.charts;

import java.util.List;

public interface Chartable {
    String getName();
    List<ChartValue> getSeries();
}
