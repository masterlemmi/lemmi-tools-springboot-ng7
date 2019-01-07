package com.lemzki.tools.people.db.controller;

import com.lemzki.tools.people.db.dto.ComplexPersonDTO;
import com.lemzki.tools.people.db.dto.PersonDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


public interface PersonController {

     static final String REST_BASE="/people";

    @GetMapping(REST_BASE +"/")
    String home();

    @GetMapping(REST_BASE + "/everyone")
    List<PersonDTO> getAllPeople();

    @GetMapping(REST_BASE + "/recent")
    List<PersonDTO> getRecentPeople();

    //get all that matches. if exclude is present. remove those from found
    @GetMapping(REST_BASE +"/find")
    List<PersonDTO> findByNameorNickName(@RequestParam("name") String nickNameOrName,
                                         @RequestParam("exclude-ids") Optional<List<Long>> excludeList);

    @PostMapping(REST_BASE+ "/")
    PersonDTO addPerson(@RequestBody ComplexPersonDTO personDTO);

    @GetMapping(REST_BASE+ "/{id}")
    PersonDTO getPerson(@PathVariable("id") long id);

    @PutMapping(REST_BASE +"/{id}")
    PersonDTO updatePerson(@PathVariable("id") long id, @RequestBody PersonDTO personDTO);

    @DeleteMapping(REST_BASE + "/{id}")
    void deletePerson(@PathVariable("id") long id);
}
