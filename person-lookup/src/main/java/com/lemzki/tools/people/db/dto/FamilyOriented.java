package com.lemzki.tools.people.db.dto;

import java.util.List;
import java.util.Set;

public interface FamilyOriented {
    List<PersonDTO> getChildren();
    Set<PersonDTO> getParents();
}
