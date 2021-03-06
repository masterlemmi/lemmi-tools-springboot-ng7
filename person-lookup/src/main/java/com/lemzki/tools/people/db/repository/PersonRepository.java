package com.lemzki.tools.people.db.repository;

import com.lemzki.tools.people.db.model.PersonDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface PersonRepository extends JpaRepository<PersonDb, Long> {

    //@Query("select p from PersonDb p where p.name like %:nickNameOrName% OR p.nickname like %:nickNameOrName%")
    List<PersonDb> findByFirstNameContainingIgnoreCaseOrNicknameContainingIgnoreCase(String firstName, String nickname);

    Optional<PersonDb> findByResourceName(String resourceName);
}