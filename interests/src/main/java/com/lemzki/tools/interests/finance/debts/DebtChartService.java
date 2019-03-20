package com.lemzki.tools.interests.finance.debts;

import com.google.common.collect.Lists;
import com.lemzki.tools.charts.ChartMultiValue;
import com.lemzki.tools.charts.ChartService;

import com.lemzki.tools.charts.ChartValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Service("debtService") public class DebtChartService implements ChartService {
    @Autowired
    DebtRepository repository;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M/d/yy");


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
                .map(d -> new ChartMultiValue(d.getName(), seriesBuilder.build(d.averagePerMonth())))
                .collect(toList());
    }

    @Override public List<ChartMultiValue> getChartItem(String chartItem,  Optional<String> from, Optional<String> to,
        Optional<Boolean> showBurnDown) {

        showBurnDown = Optional.of(true);
        if (StringUtils.isEmpty(chartItem)){
            throw new IllegalArgumentException("Chart Item is required");
        }

        //default show everything
        LocalDate fromRange = from.isPresent()  ? LocalDate.parse(from.get(),dtf) : LocalDate.of(2000, 1,1);
        LocalDate  toRange = to.isPresent()  ? LocalDate.parse(to.get(),dtf) : LocalDate.of(3000, 1,1);

        log.info("Request for Debt Chart Data for " + chartItem  + " from " + fromRange + " to " + toRange);

        Debt debt = debtService.getDebtByNameDuesByRange(chartItem.toUpperCase(), fromRange, toRange);
        ChartMultiValue debtChart = new ChartMultiValue(debt.getName(), seriesBuilder.build(debt.averagePerMonth()));
        List<ChartMultiValue> list = Lists.newArrayList(debtChart);

        if (showBurnDown.isPresent() && showBurnDown.get()){
            list.add(generateBurnDownChart(debt));
        }



        return list;

    }

    private ChartMultiValue generateBurnDownChart(Debt debt) {
        debt.av

        List<ChartValue> series = null;
        return new ChartMultiValue("Burn Down", series);
    }

    private final ChartValue.Builder<LocalDate, Double> seriesBuilder = new ChartValue.Builder<LocalDate, Double>()
        .usingKeyMapper(k-> k.getMonth() + " " + k.getYear())
        .usingValueMapper(v-> String.format("%.2f", v))
        .orderBy( comparing(Map.Entry::getKey));








}

