package com.lemzki.tools.people.db.exception;

import javassist.NotFoundException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor()
public class PersonNotFoundException extends RuntimeException {
}
