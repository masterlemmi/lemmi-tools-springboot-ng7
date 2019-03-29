package com.lemzki.tools.interests.finance.debts;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface DebtService {

     Set<Debt> getDebts(LocalDate from, LocalDate to);

    Debt getDebtByName(String chartItem);

    Debt getDebtByNameDuesByRange(String debtName, LocalDate from, LocalDate to);

    List<Debt> getAllDebts();

}
