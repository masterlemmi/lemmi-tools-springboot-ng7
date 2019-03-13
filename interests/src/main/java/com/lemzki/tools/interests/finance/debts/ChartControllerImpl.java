package com.lemzki.tools.interests.finance.debts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Predicate;

@RestController public class ChartControllerImpl implements ChartController {

    @Autowired
    ChartServiceFinder chartSvcFinder;


    @Override public List<ChartMultiValue> getChart(String chartName) {
        ChartService chartService =  chartSvcFinder.getService(matchesName(chartName));
        return chartService.getChart();
    }

    private Predicate<ChartService> matchesName(String chartName) {
        return svc -> svc.chartName().equalsIgnoreCase(chartName);
    }

}
