package com.lemzki.tools.people.db.service.impl;

import com.lemzki.tools.people.db.model.RelationType;
import com.lemzki.tools.people.db.repository.RelationTypeRepository;
import com.lemzki.tools.people.db.service.RelationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationTypeServiceImpl implements RelationTypeService {

    @Autowired
    RelationTypeRepository relationTypeRepository;

    @Override
    public RelationType retrieveOrSave(RelationType relationType) {
        return relationTypeRepository.findById(relationType.getName()).orElse(relationTypeRepository.save(relationType));
    }

}
