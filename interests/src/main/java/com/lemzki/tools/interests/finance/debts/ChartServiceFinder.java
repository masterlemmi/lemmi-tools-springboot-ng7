package com.lemzki.tools.interests.finance.debts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class ChartServiceFinder implements ServiceFinder<ChartService> {

    @Autowired
    List<ChartService> services = new ArrayList<>();

    @Override
    public ChartService getService(Predicate<ChartService> condition) {
        return services.stream().filter(condition).findFirst()
            .orElseThrow(() -> new RuntimeException("No Matching Services Found"));
    }
}
