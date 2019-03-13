package com.lemzki.tools.interests.finance.debts;

import com.google.common.collect.Lists;
import com.lemzki.tools.reader.CSVResourceReader;
import com.lemzki.tools.reader.CsvData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DebtServiceImpl implements DebtService{
    @Autowired CSVResourceReader reader;
    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");


    @Override
    public List<Debt> getDebts() {
        CsvData csvData = reader.read("debts.csv");
        Map<String, List<Debt>> map =  csvData.getRecords().stream()
           // .filter(csv->csv.get(2).equalsIgnoreCase("CAR_LOAN"))
            .map(csv->{
                Debt debt = new Debt();
                LocalDate date = LocalDate.parse(csv.get(0));
                Double amt = Double.parseDouble(csv.get(1));
                String name = csv.get(2);
                debt.setName(name);
                List<Due> dues = Lists.newArrayList(new Due(date, amt));
                debt.setDues(dues);
                return debt;
            }).collect(Collectors.groupingBy(
                Debt::getName,
                Collectors.toList()
            ));

        List<Debt> finallist = new ArrayList<>();
       for (Map.Entry<String, List<Debt>> entry: map.entrySet()){
          String debtName =  entry.getKey();
          List<Debt> debtDetails = entry.getValue();
           List<Due> aggrDues = new ArrayList<>();

           for(Debt d: debtDetails){
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
