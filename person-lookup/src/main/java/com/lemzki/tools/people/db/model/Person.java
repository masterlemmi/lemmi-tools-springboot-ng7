package com.lemzki.tools.people.db.model;

import com.lemzki.tools.people.db.dto.PersonDTO;
import com.lemzki.tools.people.db.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class Person {
    @Id @GeneratedValue
    private Long id;
    private @NonNull String name;
    private @NonNull String addedBy;
    private String nickname;
    private String photoUrl;

    @Enumerated(EnumType.STRING)
    private @NonNull Gender gender;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;
}