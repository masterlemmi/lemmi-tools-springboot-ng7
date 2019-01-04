package com.lemzki.tools.people.db.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class RelationLookUp {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private RelationType male;
    @OneToOne
    private RelationType female;
    @OneToOne
    private RelationLookUp opposite;
}