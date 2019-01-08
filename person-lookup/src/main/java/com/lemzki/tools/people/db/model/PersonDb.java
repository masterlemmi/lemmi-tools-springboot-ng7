package com.lemzki.tools.people.db.model;

import com.lemzki.tools.people.db.dto.PersonDTO;
import com.lemzki.tools.people.db.enums.Gender;
import com.lemzki.tools.security.model.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class PersonDb {
    @Id @GeneratedValue
    private Long id;
    private @NonNull String name;
    private @NonNull Long addedBy;
    private String nickname;
    private String photoUrl;

    @Enumerated(EnumType.STRING)
    private @NonNull Gender gender;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;

    private String resourceName;

    @ManyToOne
    private User user;
}