package com.lemzki.tools.interests.finance.debts;

import java.util.List;

public interface ChartService {

    List<ChartMultiValue> getChart();

    /**
     * Identifies the service implementation to service users
     * @return what chart data this returns (e.g. debts, grades, weight etc)
     */
     String chartName();
}
