package com.lemzki.tools.interests.finance.debts;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ChartController {
    @GetMapping("/charts/{chartName}")
    List<ChartMultiValue> getChart(@PathVariable("chartName") String chartName);

    @GetMapping("/charts/{chartName}")
    List<ChartMultiValue> getChart(@PathVariable("chartName") String chartName);


}
