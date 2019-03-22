package com.lemzki.tools.charts;

import com.lemzki.tools.base.RestBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@RestController public class ChartControllerImpl  implements ChartController {

    @Autowired
    ChartServiceFinder chartSvcFinder;

    @Override public List<ChartMultiValue> getChart(String chartName, Optional<String> from,
        Optional<String> to) {
        ChartService chartService =  chartSvcFinder.getService(byName(chartName));
        return chartService.getChart();
    }

    @Override public List<ChartMultiValue> getChartItem(String chartName, String chartItem, Optional<String> from,
        Optional<String> to) {
        ChartService chartService =  chartSvcFinder.getService(byName(chartName));

        return  chartService.getChartItem(chartItem, from, to, null);
    }

    private Predicate<ChartService> byName(String chartName) {
        return svc -> svc.chartName().equalsIgnoreCase(chartName);
    }

}
