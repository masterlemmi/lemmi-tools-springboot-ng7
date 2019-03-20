package com.lemzki.tools.charts;

import java.util.List;
import java.util.Optional;

public interface ChartService {

    /**
     * Identifies the service implementation to service users
     * @return what chart data this returns (e.g. debts, grades, weight etc)
     */
     String chartName();

    List<ChartMultiValue> getChart();

    List<ChartMultiValue> getChartItem(String chartItem, Optional<String> from,
        Optional<String> to, Optional<Boolean> showBurnDown);

    void insertTestData();
}
