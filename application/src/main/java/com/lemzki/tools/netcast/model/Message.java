package com.lemzki.tools.netcast.model;


import com.google.common.collect.Sets;
import com.lemzki.tools.people.db.model.PersonDb;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


//maps a parent to its children
@Entity
@Table(name="MESSAGE")
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class Message {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String message;
    private String number;

}