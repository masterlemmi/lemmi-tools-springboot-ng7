package com.lemzki.tools.people.db.service;

import com.lemzki.tools.people.db.model.RelationType;

public interface RelationTypeService {
    public RelationType retrieveOrSave(RelationType relationType);
}
