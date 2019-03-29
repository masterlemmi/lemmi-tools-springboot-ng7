package com.lemzki.tools.charts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@RestController public class ChartControllerImpl  implements ChartController {

    @Autowired
    ChartServiceFinder chartSvcFinder;

    @Override public List<ChartMultiValue> getCharts(String chartName, Optional<String> from,
        Optional<String> to) {
        ChartService chartService =  chartSvcFinder.getService(byName(chartName));
        return chartService.getCharts(from,to);
    }

    @Override public ChartMultiValue getChartItem(String chartName, String chartItem, Optional<String> from,
        Optional<String> to, Optional<Double> payment) {
        ChartService chartService =  chartSvcFinder.getService(byName(chartName));

        return  chartService.getChartItem(chartItem, from, to, payment);
    }

    @Override public List<ChartNameDto> getChartItemNames(String chartName) {
        ChartService chartService =  chartSvcFinder.getService(byName(chartName));
        return chartService.getChartItemNames();
    }

    private Predicate<ChartService> byName(String chartName) {
        return svc -> svc.chartName().equalsIgnoreCase(chartName);
    }

}
