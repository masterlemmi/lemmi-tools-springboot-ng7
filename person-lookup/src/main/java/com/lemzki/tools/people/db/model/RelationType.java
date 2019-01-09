package com.lemzki.tools.people.db.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class RelationType {
    @Id
    private String name;

    public RelationType(String name){
        this.name = name;
    }

}