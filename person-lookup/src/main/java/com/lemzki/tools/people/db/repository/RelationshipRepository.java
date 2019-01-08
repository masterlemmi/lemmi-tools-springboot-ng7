package com.lemzki.tools.people.db.repository;

import com.lemzki.tools.people.db.model.PersonDb;
import com.lemzki.tools.people.db.model.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;
import java.util.Set;

@RepositoryRestResource
public interface RelationshipRepository extends JpaRepository<Relationship, Long> {
    Set<Relationship> findByMain(PersonDb personDb);

    Optional<Relationship> findByMainAndOther(PersonDb main, PersonDb other);
}