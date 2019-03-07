package com.lemzki.tools.interests.translations;


import com.lemzki.tools.exception.ResourceNotFoundException;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
class PhraseControllerImpl implements PhraseController {

    @Autowired
    PhraseService service;



    @Override
    public List<Phrase> getAllWords() {
        return service.getAllWords();
    }

    @Override
    public ResponseEntity<Phrase> addWord(@Valid Phrase phrase) {
        Phrase savedPhrase = service.saveWord(phrase);
        return ResponseEntity.status(Response.SC_CREATED).body(savedPhrase);

    }

    @Override
    public void deleteWord(long id) {

        if (service.findWord(id) == null){
            throw  new ResourceNotFoundException("Unable to find resource with Id " + id);
        }

        service.deleteWord(id);
    }

    @Override
    public void updateWord(long id, Phrase phrase) {
        service.updateWord(id, phrase);

    }
    @Override
    public void seedDatabase(@RequestParam("filename") String filename) {
        service.seedDatabase(filename);
    }
}
