package com.lemzki.tools.interests.finance.debts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Service public class DebtChartService implements ChartService {

    private static final String CHART_NAME = "debts";

    @Autowired DebtService debtService;

    @Override public String chartName() {
        return CHART_NAME;
    }

    @Override public List<ChartMultiValue> getChart() {
        List<Debt> debts = debtService.getDebts();
        return debts.stream()
                .map(debt ->new ChartMultiValue(debt.getName(), seriesBuilder.build(debt.averagePerMonth())))
                .collect(toList());
    }

    private ChartValue.Builder<LocalDate, Double> seriesBuilder = new ChartValue.Builder<LocalDate, Double>()
            .usingKeyMapper(k-> k.getMonth() + " " + k.getYear())
            .usingValueMapper(v-> String.format("%.2f", v))
            .orderBy( comparing(Map.Entry::getKey));

}

