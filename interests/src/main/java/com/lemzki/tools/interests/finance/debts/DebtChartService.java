package com.lemzki.tools.interests.finance.debts;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lemzki.tools.charts.ChartMultiValue;
import com.lemzki.tools.charts.ChartNameDto;
import com.lemzki.tools.charts.ChartService;

import com.lemzki.tools.charts.ChartValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

import static java.time.temporal.TemporalAdjusters.*;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Service("debtService") public class DebtChartService implements ChartService {
    @Autowired DebtRepository repository;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M/d/yy");

    private static final String CHART_NAME = "debts";

    @Autowired DebtService debtService;

    @Autowired DebtAnalyzer debtAnalyzer;

    @Override public void insertTestData() {
        debtService.getAllDebts().forEach(debt -> {
            debt.getDues().forEach(due -> {
                due.setDebt(debt);
            });
            repository.save(debt);
        });
    }





    @Override public String chartName() {
        return CHART_NAME;
    }

    @Override public List<ChartMultiValue> getCharts(Optional<String> from, Optional<String> to) {
        Set<Debt> debts = debtService.getDebts(defaultFrom(from), defaultTo(to));
        return debts.stream().map(this::generateMainChart).collect(toList());
    }

    @Override public List<ChartNameDto> getChartItemNames() {
        List<ChartNameDto> debts = debtService.getAllDebts().stream()
            .map(d -> new ChartNameDto(d.getName(), d.getUiName())).collect(toList());
        debts.add(new ChartNameDto("All", "All Debts"));
        return debts;
    }

    @Override public ChartMultiValue getChartItem(String chartItem, Optional<String> from,
        Optional<String> to, Optional<Double> plannedPayment) {

        if (StringUtils.isEmpty(chartItem)) {
            throw new IllegalArgumentException("Chart Item is required");
        }


        Debt debt = debtService
            .getDebtByNameDuesByRange(chartItem.toUpperCase(), defaultFrom(from), defaultTo(to));
        ChartMultiValue debtChart = generateMainChart(debt);

        DebtAnalysis analysis = debtAnalyzer.analyze(debt, plannedPayment);
        debtChart.setDetails(generateDetailsData(analysis));
        return debtChart;

    }

    private Map<String, Object> generateDetailsData(DebtAnalysis analysis) {
        //there will always be a dt so no risk for NPE here
        DebtTrend dt = analysis.getTrend();
        DebtPaymentCalculation dpc = analysis.getCalculation();

        Map<String, Object> details = Maps.newHashMap();

        details.put("trend", getBurnDownChart(dt));
        details.put("trendNotes", dt.getNotes());
        details.put("calculation", getCalculationChart(dpc));
        details.put("calcNotes", dpc == null? "" : dpc.getNotes());


        return details;
    }

    private ChartMultiValue getCalculationChart(DebtPaymentCalculation calculation) {
        if (calculation == null || calculation.getEstimatedDues() == null) return null;

        Map<LocalDate, Double> dues = calculation.getEstimatedDues();
        List<ChartValue> series = dues.entrySet().stream()
            .map(set-> new ChartValue(set.getKey(), set.getValue()))
            .collect(toList());
        return  new ChartMultiValue("Estimation", series);
    }

    private ChartMultiValue getBurnDownChart(DebtTrend trend) {
        if (trend == null) return null;

        List<ChartValue> series = Lists
            .newArrayList(new ChartValue<>(trend.getInitialDueDate(), trend.getInitialDueAmount()),
                new ChartValue<>(trend.getLastDueDate(), trend.getLastDueAmount()));

        return new ChartMultiValue("Burn Down", series);
    }

    private ChartMultiValue generateMainChart(Debt debt) {
        List<ChartValue> avePerDaySeries = mapToSeries(debt.averagePerDay());
        return new ChartMultiValue(debt.getName(), avePerDaySeries);
    }

    private List<ChartValue> mapToSeries(Map<LocalDate, Double> map) {
        return map.entrySet().stream().map(e -> new ChartValue<>(e.getKey(), e.getValue()))
            .collect(toList());
    }

    private LocalDate defaultFrom(Optional<String> from) {
        return from.isPresent() && !StringUtils.isEmpty(from.get()) ?
            LocalDate.parse(from.get(), dtf) :
            LocalDate.now().minusYears(5);
    }

    private LocalDate defaultTo(Optional<String> to) {
        return to.isPresent() && !StringUtils.isEmpty(to.get()) ?
            LocalDate.parse(to.get(), dtf) :
            LocalDate.now().with(lastDayOfYear());
    }



}

