package com.lemzki.tools.interests.finance.debts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lemzki.tools.charts.ChartValue;
import com.lemzki.tools.charts.Chartable;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Entity @Table(name = "DEBT_LOAN")
@Getter
@Setter
@ToString
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
@NoArgsConstructor

public class Debt  {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @EqualsAndHashCode.Include
    @Column(unique = true)
    private String name;
    private String uiName;
    @Column(columnDefinition = "NUMBER(3,2)")
    private double interest;

    @OneToMany(mappedBy = "debt", cascade = CascadeType.ALL, orphanRemoval = true) private List<Due>
        dues = new ArrayList<>();

    public void addDue(Due due) {
        dues.add(due);
        due.setDebt(this);
    }

    public void removeDue(Due due) {
        dues.remove(due);
        due.setDebt(null);
    }

    public double getInterestPercentage(){
       return this.getInterest() / 100d;
    }

    public DebtTrend caculateTrend(){
        Due firstDue = null;
        Due maxDue = null;
        for (Due due: getDues()){
            if (firstDue == null || due.getDate().isBefore(firstDue.getDate())){
                firstDue = due;
            }

            if (maxDue == null || due.getDate().isAfter(maxDue.getDate())){
                maxDue = due;
            }
        }

        if (maxDue == null) {
            throw new RuntimeException("Unable to calculate Trend. Debt might not have dues.");
        }

        return new DebtTrend(firstDue, maxDue);
    }

    @JsonIgnore
    public Map<LocalDate, Double> averagePerDay() {
        return dues.stream()
            .collect(
                groupingBy(byDate, TreeMap::new, averagingDouble(Due::getAmount)));
    }

    @JsonIgnore
    @Transient
    private Function<Due, LocalDate> firstOfMonthFnc = due -> due.getDate().withDayOfMonth(1);

    @JsonIgnore
    @Transient
    private Function<Due, LocalDate> byDate = due -> due.getDate();

}
