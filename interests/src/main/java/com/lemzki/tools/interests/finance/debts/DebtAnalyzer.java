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

            if (lastAmt > dt.getLastDueAmount()) {
                DebtPaymentCalculation impossible =  impossibleDebt(payment.get(), debt.getUiName());
                analysis.setCalculation(impossible);
                return analysis;
            }
            estimateFinish = estimateFinish.plusMonths(1);
            futureDues.put(estimateFinish, lastAmt);
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

    private DebtPaymentCalculation impossibleDebt(double payment, String debtName) {
        DebtPaymentCalculation dc = new DebtPaymentCalculation();
        dc.setDuration(Double.POSITIVE_INFINITY);
        dc.setEstimatedEnd(LocalDate.MAX);
        dc.setPayment(payment);
        dc.setCanBePaid(false);
        return dc;
    }
}
