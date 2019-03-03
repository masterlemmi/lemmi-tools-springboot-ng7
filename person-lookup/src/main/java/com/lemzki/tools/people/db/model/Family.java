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
    private @NonNull Long addedBy;
    @OneToOne
    private PersonDb parent;
    @ManyToMany
    private Set<PersonDb> children = new HashSet<>();

    public Family(PersonDb parent){
        this.parent = parent;
    }

    public Family (PersonDb parent, PersonDb... kids){
        this.parent = parent;
        children.addAll(Sets.newHashSet(kids));
    }

    public Family (PersonDb parent, Set<PersonDb> kids){
        this.parent = parent;
        this.children.addAll(kids);
    }


    public void childrenAre(PersonDb... chichi) {
        children.addAll(Sets.newHashSet(chichi));
    }
}