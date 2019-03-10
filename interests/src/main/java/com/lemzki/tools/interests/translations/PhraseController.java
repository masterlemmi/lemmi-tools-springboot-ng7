package com.lemzki.tools.interests.translations;


import com.lemzki.tools.base.RestBaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface PhraseController extends RestBaseController {

    @GetMapping("/phrases")
    List<Phrase> getAllWords();

    @PostMapping("/phrases")
    ResponseEntity<Phrase> addWord(@RequestBody Phrase phrase);

    @DeleteMapping("/phrases/{id}")
    void deleteWord(@PathVariable("id") long id);

    @PutMapping("/phrases/{id}")
    void updateWord(@PathVariable("id") long id, @RequestBody Phrase phrase);

    @GetMapping("/phrases/seed")
    void seedDatabase(@RequestParam("filename") String filename);
}
