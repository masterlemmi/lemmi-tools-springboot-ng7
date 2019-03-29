package com.lemzki.tools.interests.finance.debts;

import com.google.common.collect.Lists;
import com.lemzki.tools.exception.ResourceNotFoundException;
import com.lemzki.tools.reader.CSVResourceReader;
import com.lemzki.tools.reader.CsvData;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Service
public class DebtServiceImpl implements DebtService {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired CSVResourceReader reader;

    @Autowired DebtRepository repository;

    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M/d/yy");



    @Override public List<Debt> getAllDebts() {
        return repository.findAll();
    }

    @Override public Debt getDebtByNameDuesByRange(String chartItem, LocalDate from, LocalDate to) {
        log.info("Request for Debt Chart Data for " + chartItem + " from " + from + " to " + to);
        Debt debt = repository.findDebt(chartItem, from, to);

        if (debt == null) {
            throw new ResourceNotFoundException(
                "Unable to find chart data for " + chartItem + " from:" + from + " to:" + to);
        }

        return debt;
    }

    @Override public Debt getDebtByName(String item) {
        return repository.findByName(item);
    }

    @Override public Set<Debt> getDebts(LocalDate from, LocalDate to) {
        return repository.findAllDebtByDuesDate(from, to);
    }


    private List<Debt> getDebtsFromResource(Predicate<CSVRecord> filter) {
        CsvData csvData = reader.read("debts.csv");
        Map<String, List<Debt>> map = csvData.getRecords().stream().filter(filter).map(csv -> {
            Debt debt = new Debt();
            LocalDate date = LocalDate.parse(csv.get(0), dtf);
            Double amt = Double.parseDouble(csv.get(1));
            String name = csv.get(2);
            debt.setName(name);
            List<Due> dues = Lists.newArrayList(new Due(date, amt));
            debt.setDues(dues);
            return debt;
        }).collect(Collectors.groupingBy(Debt::getName, Collectors.toList()));

        List<Debt> finallist = new ArrayList<>();
        for (Map.Entry<String, List<Debt>> entry : map.entrySet()) {
            String debtName = entry.getKey();
            List<Debt> debtDetails = entry.getValue();
            List<Due> aggrDues = new ArrayList<>();

            for (Debt d : debtDetails) {
                aggrDues.addAll(d.getDues());
            }

            Debt debt = new Debt();
            debt.setName(debtName);
            debt.setDues(aggrDues);
            finallist.add(debt);
        }
        return finallist;
    }

}
