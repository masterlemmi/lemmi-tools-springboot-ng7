package com.lemzki.tools.people.db.model;

import com.google.common.collect.Sets;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


//maps a parent to its children
@Entity
        @Table(name="PARENT")
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class Family {
    @Id @GeneratedValue
    private Long id;
    @OneToOne
    private Person parent;
    @ManyToMany
    private Set<Person> children = new HashSet<>();

    public Family(Person parent){
        this.parent = parent;
    }

    public Family (Person parent, Person... kids){
        this.parent = parent;
        children.addAll(Sets.newHashSet(kids));
    }

    public Family (Person parent, Set<Person> kids){
        this.parent = parent;
        this.children.addAll(kids);
    }


    public void childrenAre(Person... chichi) {
        children.addAll(Sets.newHashSet(chichi));
    }
}