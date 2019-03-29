package com.lemzki.tools.interests.finance.debts;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

@Getter
@Setter
@ToString
public class DebtPaymentCalculation {
    private boolean canBePaid;
    private LocalDate estimatedEnd;
    private double payment;
    private double duration;
    private TreeMap<LocalDate, Double> estimatedDues;


    public String getNotes(){
        if (canBePaid){
           return "With a consistent payment of " + payment + " per month " +
                "you will pay all your dues in " + refactoredDuration() + ". That' s in " + estimatedEnd + "!";
        } else {
           return  "Tough luck. with a payment of only " + payment + " per month " +
                "you'll be in debt forever!";
        }
    }

    public String refactoredDuration(){
        return duration > 11 ? duration  / 12 + " years " + duration % 12 +  " + months" :
            duration + " months";
    }
}
