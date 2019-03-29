package com.lemzki.tools.interests.finance.debts;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class DebtAnalysis {
    private String debtName;
    private DebtTrend trend;
    private DebtPaymentCalculation calculation;
    private List<String> trendNotes;

    public DebtAnalysis(Debt debt){
        this.debtName = debtName;
        this.trend = debt.caculateTrend();
        this.calculation = null;
    }




}
