package com.lemzki.tools.people.db.repository;

import com.lemzki.tools.people.db.dto.PersonDTO;
import com.lemzki.tools.people.db.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface PersonRepository extends JpaRepository<Person, Long> {

    //@Query("select p from Person p where p.name like %:nickNameOrName% OR p.nickname like %:nickNameOrName%")
    List<Person> findByNameContainingIgnoreCaseOrNicknameContainingIgnoreCase(String name, String nicknamea);
}