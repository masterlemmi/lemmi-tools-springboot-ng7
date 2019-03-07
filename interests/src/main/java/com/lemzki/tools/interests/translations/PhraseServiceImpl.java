package com.lemzki.tools.interests.translations;


import com.lemzki.tools.exception.ResourceNotFoundException;
import com.lemzki.tools.reader.CSVResourceReader;
import com.lemzki.tools.reader.CsvData;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.function.Function;

@Service
class PhraseServiceImpl implements PhraseService {



    @Autowired
    CSVResourceReader resourceReader;

    @Autowired
    PhraseRepository repository;

    private Function<CsvData, List<?>> wordMapper = (csvData) -> {
        String[] headers = csvData.getHeaders();
        List<CSVRecord> records = csvData.getRecords();

        return null;
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

    @Override public List<Phrase> seedDatabase(String filename) {
         return resourceReader.read(filename)
            .mapResults(wordMapper);
    }


}
