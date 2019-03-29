package com.lemzki.tools.charts;

import java.util.List;
import java.util.Optional;

public interface ChartService {

    /**
     * Identifies the service implementation
     * @return what chart data this returns (e.g. debts, grades, weight etc)
     */
     String chartName();

    List<ChartMultiValue> getCharts(Optional<String> from, Optional<String> to);

    ChartMultiValue getChartItem(String chartItem, Optional<String> from,
        Optional<String> to, Optional<Double> plannedPayment);

    List<ChartNameDto> getChartItemNames();

    void insertTestData();
}
