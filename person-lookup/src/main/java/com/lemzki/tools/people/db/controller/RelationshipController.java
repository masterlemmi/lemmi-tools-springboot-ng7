package com.lemzki.tools.people.db.controller;

import com.lemzki.tools.people.db.dto.ComplexPersonDTO;
import com.lemzki.tools.people.db.dto.PersonDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface RelationshipController {

    @GetMapping("/people/{id}/relationships")
    Map<String, Set<PersonDTO>> getRelationships(@PathVariable("id") long personId);

    @PostMapping("/people/{id}/relationships")
    Map<String, Set<PersonDTO>> addRelationship(@PathVariable("id") long id, @RequestBody Map<String, Set<PersonDTO>> relationships);

    @PutMapping("/people/{id}/relationships/{relationType}")
    PersonDTO updateRelationship(@PathVariable("id") long id, @PathVariable("relationType") String relationType, @RequestBody Set<PersonDTO> relationshipskkkk);

    @DeleteMapping("/people/{id}/{relationType}")
    void deleteRelationship(@PathVariable("id") long id);
}
