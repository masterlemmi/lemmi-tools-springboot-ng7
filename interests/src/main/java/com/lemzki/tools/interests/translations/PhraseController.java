package com.lemzki.tools.interests.translations;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface PhraseController {

    String REST_BASE = "/api/ide";

    @GetMapping(REST_BASE + "/plugins")
    List<Phrase> getAllWords();

    @PostMapping(REST_BASE + "/plugins")
    ResponseEntity<Phrase> addWord(@RequestBody Phrase phrase);

    @DeleteMapping(REST_BASE + "/plugins/{id}")
    void deleteWord(@PathVariable("id") long id);

    @PutMapping(REST_BASE + "/plugins/{id}")
    void updateWord(@PathVariable("id") long id, @RequestBody Phrase phrase);
}
