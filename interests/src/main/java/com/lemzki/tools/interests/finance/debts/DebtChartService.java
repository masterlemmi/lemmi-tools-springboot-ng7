package com.lemzki.tools.interests.finance.debts;

import com.google.common.collect.Lists;
import com.lemzki.tools.charts.ChartMultiValue;
import com.lemzki.tools.charts.ChartService;

import com.lemzki.tools.charts.ChartValue;
import org.apache.commons.text.CaseUtils;
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
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.*;
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
                .map(d -> new ChartMultiValue(d.getName(), mapToSeries(d.averagePerMonth())))
                .collect(toList());
    }

    @Override public List<ChartMultiValue> getChartItem(String chartItem,  Optional<String> from, Optional<String> to,
        Optional<Boolean> showBurnDown) {

        showBurnDown = Optional.of(true);
        if (StringUtils.isEmpty(chartItem)){
            throw new IllegalArgumentException("Chart Item is required");
        }

        //default show everything
        LocalDate fromRange = from.isPresent()  ?
            LocalDate.parse(from.get(), dtf) : LocalDate.now().with(firstDayOfYear());

        LocalDate  toRange = to.isPresent()  ?
            LocalDate.parse(to.get(),dtf) :  LocalDate.now().with(lastDayOfYear());

        log.info("Request for Debt Chart Data for " + chartItem  + " from " + fromRange + " to " + toRange);

        Debt debt = debtService.getDebtByNameDuesByRange(chartItem.toUpperCase(), fromRange, toRange);
        Map<LocalDate, Double> avePerMonth = debt.averagePerMonth();

        insertMissingMonths(avePerMonth);

        List<ChartValue> avePerMonthSeries = mapToSeries(avePerMonth);

        ChartMultiValue debtChart = new ChartMultiValue(debt.getName(), avePerMonthSeries);
        List<ChartMultiValue> list = Lists.newArrayList(debtChart);

        if (showBurnDown.isPresent() && showBurnDown.get()){
            list.add(generateBurnDownChart((TreeMap) avePerMonth));
        }



        return list;

    }

    //Map Implementaiton is TreeMap so dates should already be ordered
    private void insertMissingMonths(Map<LocalDate, Double> avePerMonth) {

        if (CollectionUtils.isEmpty(avePerMonth)){
            log.debug("No avePerMonth to find Missing Months for");
            return;
        }

        TreeMap<LocalDate, Double> castedMap = (TreeMap<LocalDate, Double>) avePerMonth;

        LocalDate start = castedMap.firstKey();
        LocalDate end = castedMap.lastKey();
        Double lastDue = castedMap.get(start);

        for (;start.isBefore(end) || start.isEqual(end);
             start = start.with(TemporalAdjusters.firstDayOfNextMonth())) {

            if(!avePerMonth.containsKey(start)){
                log.debug("Due is missing for Date " + start + " Copying data from previous date");
                avePerMonth.put(start, lastDue); //copy last due if missing
            } else {
                lastDue = avePerMonth.get(start);
            }
        }
    }

    //calculate average payment made for the entire given range in the map
    private ChartMultiValue generateBurnDownChart(TreeMap<LocalDate, Double> avePerMonth) {
        Double firstDue = avePerMonth.get(avePerMonth.firstKey());
        Double  lastDue = avePerMonth.get(avePerMonth.lastKey());
        Double aveRise = (lastDue - firstDue) / (double) avePerMonth.size();

        List<ChartValue> series = Lists.newArrayList(
            buildChartValue(avePerMonth.firstKey(), firstDue),
            buildChartValue(avePerMonth.lastKey(), lastDue)
        );

        if (aveRise < 0) {
            //there's a chance of payikng all dues
            long months = (long) Math.abs(lastDue /aveRise);
            LocalDate targetDate = avePerMonth.lastKey().plusMonths(months);
            series.add(buildChartValue(targetDate, 0d));
        }

        return new ChartMultiValue("Burn Down", series);
    }

    private ChartValue buildChartValue(LocalDate date, Double due){
        return new ChartValue(getMonthYear.apply(date), dueAsString.apply(due));
    }

    private  List<ChartValue> mapToSeries(Map<LocalDate, Double> map){
       return map.entrySet()
            .stream()
            .map(entry-> {
                String name = getMonthYear.apply(entry.getKey());
                String value = dueAsString.apply(entry.getValue());
                return new ChartValue(name, value);
            })
            .collect(toList());
    }

    private Function<LocalDate, String> getMonthYear = date -> CaseUtils
        .toCamelCase(date.getMonth().toString(), true) + " " + date.getYear();

    private Function<Double, String> dueAsString = due -> String.format("%.2f", due);
}

