package com.lemzki.tools.security;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity @Table(name = "APP_USERS") @Getter @Setter @NoArgsConstructor @ToString
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


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

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

    @JsonProperty("roles")
    public String[] getRolesAsStringArray() {
        return this.roles.stream()
            .map(Role::getName)
            .toArray(String[]::new);
    }
}
