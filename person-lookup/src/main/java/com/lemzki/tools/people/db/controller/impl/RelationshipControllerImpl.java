package com.lemzki.tools.people.db.controller.impl;

import com.lemzki.tools.people.db.controller.RelationshipController;
import com.lemzki.tools.people.db.dto.PersonDTO;

import java.util.Map;
import java.util.Set;

public class RelationshipControllerImpl implements RelationshipController {
    @Override
    public Map<String, Set<PersonDTO>> getRelationships(long personId) {
        return null;
    }

    @Override
    public Map<String, Set<PersonDTO>> addRelationship(long id, Map<String, Set<PersonDTO>> relationships) {
        return null;
    }

    @Override
    public PersonDTO updateRelationship(long id, String relationType, Set<PersonDTO> relationshipskkkk) {
        return null;
    }

    @Override
    public void deleteRelationship(long id) {

    }
}
