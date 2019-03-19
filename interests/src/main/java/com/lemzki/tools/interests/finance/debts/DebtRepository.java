package com.lemzki.tools.interests.finance.debts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface DebtRepository extends JpaRepository<Debt, Long> {
    @Query("from Debt D  join fetch D.dues Ds where D.name =:name AND Ds.date >= :from AND Ds.date <= :to")
    Set<Debt> findDebt(String name, LocalDate from, LocalDate to);

    List<Debt> findByName(String name);
}
