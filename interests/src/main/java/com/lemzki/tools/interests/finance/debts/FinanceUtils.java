package com.lemzki.tools.interests.finance.debts;

public class FinanceUtils {
    private FinanceUtils(){}

    public static double  applyInterest(double principal, double percentage){
        return principal + (principal*percentage);
    }

    public static double  payWithInterest(double principal, double percentage, double payment){
        return applyInterest(principal, percentage) - payment;
    }
}
