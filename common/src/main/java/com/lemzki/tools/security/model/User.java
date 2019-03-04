package com.lemzki.tools.security.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.*;


@Entity @Table(name = "user") @Getter @Setter @NoArgsConstructor @ToString
@EqualsAndHashCode (exclude = "roles")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    private String googleId;
    private String name;
    private String email;
    private String link;
    private String picture;
    private String gender;
    private String accessToken;


    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = Role.DEFAULT_ROLES;

    public void addRole(Role role) {
         role.getUsers().add(this);
         this.roles.add(role);
    }

    public void removeRole(Role role) {
        role.getUsers().remove(this);
        this.roles.remove(role);
    }

    public User(String name) {
        this.name = name;
    }

    public static User ANONYMOUS = new User("Anonymous");

    public User updateFrom(User user) {
        //using data from param, update relevant fields
        //id remains the same so that db connection is unchanged
        this.setName(user.getName());
        this.setPicture(user.getPicture());
        this.setGender(user.getGender());
        this.setLink(user.getLink());
        this.setEmail(user.getEmail());
        this.setAccessToken(user.getAccessToken());

        return this;
    }

    public String[] getRolesAsStringArray() {
        return this.roles.stream()
            .map(Role::getName)
            .toArray(String[]::new);
    }
}
