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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
