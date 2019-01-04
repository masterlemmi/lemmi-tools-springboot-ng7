package com.lemzki.tools.people.db.repository;

import com.lemzki.tools.people.db.model.RelationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RelationTypeRepository extends JpaRepository<RelationType, String> {
    RelationType findByName(String key);


}