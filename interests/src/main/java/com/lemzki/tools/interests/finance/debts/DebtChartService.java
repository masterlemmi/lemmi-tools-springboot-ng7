package com.lemzki.tools.interests.finance.debts;

import com.lemzki.tools.charts.ChartMultiValue;
import com.lemzki.tools.charts.ChartService;

import com.lemzki.tools.charts.ChartValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Service("debtService") public class DebtChartService implements ChartService {
    @Autowired
    DebtRepository repository;

    @Override public void insertTestData() {
        debtService.getDebts().forEach(debt-> {
            debt.getDues().forEach(due->{
                due.setDebt(debt);
            });
            repository.save(debt);
        });
    }

    private static final String CHART_NAME = "debts";

    @Autowired DebtService debtService;

    @Override public String chartName() {
        return CHART_NAME;
    }

    @Override public List<ChartMultiValue> getChart() {
        List<Debt> debts = debtService.getDebts();
        return debts.stream()
                .map(debtChartMapper)
                .collect(toList());
    }

    @Override public ChartMultiValue getChartItem(String chartItem,  Optional<String> from, Optional<String> to) {
       Debt debt = debtService.getDebtByName(chartItem);

        return debtChartMapper.apply(debt);
    }

    private final ChartValue.Builder<LocalDate, Double> seriesBuilder = new ChartValue.Builder<LocalDate, Double>()
        .usingKeyMapper(k-> k.getMonth() + " " + k.getYear())
        .usingValueMapper(v-> String.format("%.2f", v))
        .orderBy( comparing(Map.Entry::getKey));

    private Function<Debt, ChartMultiValue> debtChartMapper = debt ->
         new ChartMultiValue(debt.getName(), seriesBuilder.build(debt.averagePerMonth()));







}

