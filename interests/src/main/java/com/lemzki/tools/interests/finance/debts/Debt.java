package com.lemzki.tools.interests.finance.debts;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

@Entity @Table(name = "DEBT_LOAN") @Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor
@AllArgsConstructor
public class Debt implements Chartable {
    @Id
    @GeneratedValue
    private Long id;
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

    @Override public List<ChartValue> getSeries() {
        return averagePerMonth().entrySet()
            .stream()
            .map(entrySet -> {
               // String month = entrySet.getKey().name();
                Double average = entrySet.getValue();
                return new ChartValue(null, average.toString());
             }).collect(toList());
    }

    public Map<LocalDate, Double> averagePerMonth() {
        return dues.stream()
            .collect(
                groupingBy(firstOfMonthFnc, averagingDouble(Due::getAmount)));
    }

    @Transient
    private Function<Due, LocalDate> firstOfMonthFnc = due -> due.getDate().withDayOfMonth(1);
}
