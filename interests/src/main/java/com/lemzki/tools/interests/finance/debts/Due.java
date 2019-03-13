package com.lemzki.tools.interests.finance.debts;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="DUE")
@Getter
@Setter
@ToString
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Due {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    @Column(precision=12, scale=2)
    private Double amount;

    public Due(LocalDate date,Double amount){
        this.date = date;
        this.amount = amount;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "debt_id")
    private Debt debt;

}
