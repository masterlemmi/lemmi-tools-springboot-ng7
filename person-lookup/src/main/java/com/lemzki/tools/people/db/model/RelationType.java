package com.lemzki.tools.people.db.model;

import com.lemzki.tools.people.db.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

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