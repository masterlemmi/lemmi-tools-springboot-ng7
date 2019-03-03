package com.lemzki.tools.security.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Role {

    public static final Role USER = new Role("USER") ;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(String name){
        this.name = name;
    }
}
