package com.lemzki.tools.interests.finance.debts;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Getter
@ToString
public final class DebtTrend {
    private LocalDate initialDueDate;
    private LocalDate lastDueDate;
    private double initialDueAmount;
    private double lastDueAmount;
    private double slope;
    private List<String> notes;

    public DebtTrend(Due firstDue, Due lastDue) {
        this.initialDueDate = firstDue.getDate();
        this.lastDueDate = lastDue.getDate();
        this.initialDueAmount = firstDue.getAmount();
        this.lastDueAmount = lastDue.getAmount();
        this.slope = calculateSlope();
        this.notes = analysisNotes();
    }

    private double calculateSlope() {
        long monthInterval = ChronoUnit.MONTHS
            .between(initialDueDate.withDayOfMonth(1), lastDueDate.withDayOfMonth(1));
        return (lastDueAmount - initialDueAmount) / (double) monthInterval;
    }

    private List<String> analysisNotes() {
        return Lists.newArrayList(
            trendNote(),
            judgement()
        );
    }

    private String trendNote(){
        return String.format("There is %s trend of %.2f per month.",
            slope > 0 ? "an upwards" : "a downwards", slope);
    }

    private String judgement(){
        return  slope > 0 ?
            "With your current rate, you'll never pay your debts!" :
            "Keep it up you are doing well!";
    }


}
