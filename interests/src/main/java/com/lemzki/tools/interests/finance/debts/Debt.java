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
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Entity @Table(name = "DEBT_LOAN")
@Getter
@Setter
@ToString
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Debt  {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @EqualsAndHashCode.Include
    @Column(unique = true)
    private String name;

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

    @JsonIgnore
    public Map<LocalDate, Double> averagePerMonth() {
        return dues.stream()
            .collect(
                groupingBy(firstOfMonthFnc, averagingDouble(Due::getAmount)));
    }

    @JsonIgnore
    @Transient
    private Function<Due, LocalDate> firstOfMonthFnc = due -> due.getDate().withDayOfMonth(1);
}
