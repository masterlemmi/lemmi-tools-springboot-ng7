package com.lemzki.tools.people.db.repository;

import com.lemzki.tools.people.db.model.Family;
import com.lemzki.tools.people.db.model.PersonDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface FamilyRepository extends JpaRepository<Family, Long> {
    Optional<Family> findByParent(PersonDb parent);

    @Query("SELECT P from Family P INNER JOIN P.children PC where PC = :child")
    List<Family> findParents(PersonDb child);


}