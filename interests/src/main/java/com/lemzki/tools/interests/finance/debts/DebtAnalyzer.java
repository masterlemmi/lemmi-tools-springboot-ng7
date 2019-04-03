package com.lemzki.tools.interests.finance.debts;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
import java.util.TreeMap;

@Component
public class DebtAnalyzer {

    public DebtAnalysis analyze (Debt debt, Optional<Double> payment){

        DebtAnalysis analysis = new DebtAnalysis(debt);

        if (payment == null || !payment.isPresent()){
            //without payment no need to caclculate future paymentdues;
            return analysis;
        }

        DebtTrend dt = analysis.getTrend();

        double lastAmt = dt.getLastDueAmount();
        LocalDate estimateFinish = dt.getLastDueDate();
        TreeMap<LocalDate, Double> futureDues = new TreeMap<>();

        int paymentCounts = 0;
        while (lastAmt > 0) {
            paymentCounts++;
            lastAmt = FinanceUtils.payWithInterest(lastAmt, debt.getInterestPercentage(), payment.get());

            estimateFinish = estimateFinish.plusMonths(1);
            futureDues.put(estimateFinish, lastAmt);

            if (paymentCounts >= 240){
                DebtPaymentCalculation dc = new DebtPaymentCalculation();
                dc.setDuration(paymentCounts);
                dc.setEstimatedEnd(estimateFinish);
                dc.setLastAmount(lastAmt);
                dc.setEstimatedDues(futureDues);
                dc.setPayment(payment.get());
                dc.setCanBePaid(false);
                analysis.setCalculation(dc);
                return analysis;
            }
        }

        DebtPaymentCalculation dc = new DebtPaymentCalculation();
        dc.setDuration(paymentCounts);
        dc.setEstimatedEnd(estimateFinish);
        dc.setEstimatedDues(futureDues);
        dc.setPayment(payment.get());
        dc.setCanBePaid(true);

        analysis.setCalculation(dc);
        return analysis;
    }


}
