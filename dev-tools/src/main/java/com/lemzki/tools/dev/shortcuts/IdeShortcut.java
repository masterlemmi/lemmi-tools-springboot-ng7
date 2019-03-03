package com.lemzki.tools.dev.shortcuts;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SHORTCUT")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class IdeShortcut {
    @Id
    @GeneratedValue
    private Long id;
    private String eclipse;
    private String intelliJ;
    private String vsCode;
    private String description;
}
