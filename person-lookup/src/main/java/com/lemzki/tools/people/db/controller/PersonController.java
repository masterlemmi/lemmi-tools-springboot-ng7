package com.lemzki.tools.people.db.controller;

import com.lemzki.tools.people.db.dto.ComplexPersonDTO;
import com.lemzki.tools.people.db.dto.PersonDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


public interface PersonController {

    @GetMapping("/")
    String home();

    @GetMapping("/everyone")
    List<PersonDTO> getAllPeople();

    @GetMapping("/recent")
    List<PersonDTO> getRecentPeople();

    //get all that matches. if exclude is present. remove those from found
    @GetMapping("/find")
    List<PersonDTO> findByNameorNickName(@RequestParam("name") String nickNameOrName,
                                         @RequestParam("exclude-ids") Optional<List<Long>> excludeList);

    @PostMapping("/")
    PersonDTO addPerson(@RequestBody ComplexPersonDTO personDTO);

    @GetMapping("/{id}")
    PersonDTO getPerson(@PathVariable("id") long id);

    @PutMapping("/{id}")
    PersonDTO updatePerson(@PathVariable("id") long id, @RequestBody PersonDTO personDTO);

    @DeleteMapping("/{id}")
    void deletePerson(@PathVariable("id") long id);
}
