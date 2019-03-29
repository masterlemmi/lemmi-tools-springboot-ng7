package com.lemzki.tools.charts;

import com.lemzki.tools.base.RestBaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface ChartController extends RestBaseController {
    @GetMapping("/charts/{chartName}") List<ChartMultiValue> getCharts(
        @PathVariable("chartName") String chartName, Optional<String> from, Optional<String> to);

    @GetMapping("/charts/{chartName}/{chartItem}") ChartMultiValue getChartItem(
        @PathVariable("chartName") String chartName,
        @PathVariable("chartItem") String chartItem,
        Optional<String> from, Optional<String> to, Optional<Double> payment);

    @GetMapping("/charts/{chartName}/items") List<ChartNameDto> getChartItemNames(@PathVariable("chartName") String chartName);

}
