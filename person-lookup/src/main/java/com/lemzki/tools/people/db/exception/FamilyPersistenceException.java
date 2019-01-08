package com.lemzki.tools.people.db.repository.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FamilyPersistenceException extends RuntimeException {
    private String message;
}
