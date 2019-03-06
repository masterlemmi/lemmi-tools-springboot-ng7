package com.lemzki.tools.interests.translations;


import com.lemzki.tools.exception.ResourceNotFoundException;
import com.lemzki.tools.reader.CSVResourceReader;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Service
class PhraseServiceImpl implements PhraseService {

    private static final String FILE_NAME = "plugins.csv";

    @Autowired
    CSVResourceReader resourceReader;

    @Autowired
    PhraseRepository repository;

    private Function<CSVRecord, Phrase> wordMapper = (record) -> {
        return new Phrase();
    };

    @Override
    public List<Phrase> getAllWords() {
        return repository.findAll();
    }

    @Override
    public Phrase saveWord(Phrase phrase) {

        return repository.save(phrase);
    }

    @Override
    public Phrase findWord(long id) {

        return repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Unable to find resource with Id " + id));
    }

    @Override
    public void deleteWord(long id) {
        repository.delete(findWord(id));
    }

    @Override
    public void updateWord(long id, Phrase phrase) {
        Assert.isTrue(phrase.getId().equals(id));
        findWord(id);
        repository.save(phrase);
    }


    public void importWords(List<Phrase> phrases) {



    }
}
