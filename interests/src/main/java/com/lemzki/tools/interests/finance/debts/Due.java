package com.lemzki.tools.interests.finance.debts;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "DUE_DATE")
    private LocalDate date;
    @Column(columnDefinition = "NUMBER(15,2)")
    private Double amount;

    public Due(LocalDate date,Double amount){
        this.date = date;
        this.amount = amount;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "debt_id")
    private Debt debt;

}
