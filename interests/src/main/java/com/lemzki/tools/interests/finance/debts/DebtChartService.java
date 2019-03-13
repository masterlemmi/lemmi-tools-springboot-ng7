package com.lemzki.tools.interests.finance.debts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service public class DebtChartService implements ChartService {

    private static final String CHART_NAME = "debts";

    @Autowired DebtService debtService;

    @Override public String chartName() {
        return CHART_NAME;
    }

    @Override public List<ChartMultiValue> getChart() {
        List<Debt> debts = debtService.getDebts();
        return debts.stream().map(ChartMultiValue::new).collect(toList());
    }
}
