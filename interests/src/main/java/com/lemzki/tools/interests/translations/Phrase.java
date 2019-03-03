package com.lemzki.tools.interests.translations;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="PHRASE")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Phrase {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String english;
    private String cebuano;
    private String ilocano;
    private String japanese;
    private String french;
}
