package com.lemzki.tools.people.db.repository;

import com.lemzki.tools.people.db.model.PersonDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface PersonRepository extends JpaRepository<PersonDb, Long> {

    //@Query("select p from PersonDb p where p.name like %:nickNameOrName% OR p.nickname like %:nickNameOrName%")
    List<PersonDb> findByNameContainingIgnoreCaseOrNicknameContainingIgnoreCase(String name, String nicknamea);
}