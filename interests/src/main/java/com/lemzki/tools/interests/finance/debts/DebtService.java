package com.lemzki.tools.interests.finance.debts;

import java.time.LocalDate;
import java.util.List;

public interface DebtService {

     List<Debt> getDebts();

    Debt getDebtByName(String chartItem);

    Debt getDebtByNameDuesByRange(String chartItem, LocalDate from, LocalDate to);
}
