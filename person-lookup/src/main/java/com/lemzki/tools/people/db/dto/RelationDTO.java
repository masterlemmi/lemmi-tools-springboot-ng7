package com.lemzki.tools.people.db.dto;

import com.lemzki.tools.people.db.enums.GenderE;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class RelationDTO {
    private @NonNull PersonDTO other;
    private @NonNull String relation;
    private @NonNull GenderE gender;
}
