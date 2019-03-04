package com.lemzki.tools.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(exclude = "users")
public class Role {

    public static final Role USER = new Role(1L,"USER") ;
    public static final Role ADMIN = new Role(2L,"ADMIN") ;
    public static final Set<Role> DEFAULT_ROLES = Sets.newHashSet(USER);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    public Role(Long id, String name){
        this.id = id;
        this.name = name;
    }

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}
