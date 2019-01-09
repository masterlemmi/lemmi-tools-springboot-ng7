package com.lemzki.tools.people.db.dto;

import com.lemzki.tools.people.db.enums.GenderE;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
/*@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(
        {@JsonSubTypes.Type(value = ComplexPersonDTO.class, name = "complex")},
        {@JsonSubTypes.Type(value = PersonDTO.class, name = "simple")})*/
public class PersonDTO {
    private Long id;
    private String photoUrl;
    private @NonNull String name;
    private @NonNull GenderE gender;

}
