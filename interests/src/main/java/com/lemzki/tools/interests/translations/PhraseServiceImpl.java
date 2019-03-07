package com.lemzki.tools.interests.translations;


import com.lemzki.tools.exception.ResourceNotFoundException;
import com.lemzki.tools.reader.CSVResourceReader;
import com.lemzki.tools.reader.CsvData;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service class PhraseServiceImpl implements PhraseService {

    private static final Logger LOGGER = LogManager.getLogger(PhraseServiceImpl.class);

    @Autowired CSVResourceReader resourceReader;

    @Autowired PhraseRepository repository;



    @Override public List<Phrase> getAllWords() {
        return repository.findAll();
    }

    @Override public Phrase saveWord(Phrase phrase) {

        return repository.save(phrase);
    }

    @Override public Phrase findWord(long id) {

        return repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Unable to find resource with Id " + id));
    }

    @Override public void deleteWord(long id) {
        repository.delete(findWord(id));
    }

    @Override public void updateWord(long id, Phrase phrase) {
        Assert.isTrue(phrase.getId().equals(id));
        findWord(id);
        repository.save(phrase);
    }

    @Override public void seedDatabase(String filename) {
        CsvData csvData = resourceReader.read(filename);

        List<CSVRecord> records = csvData.getRecords();
        String[] headers = csvData.getHeaders();

         records.stream()
            .map(record -> mapper(record, headers))
            .forEach(this::saveWord);
    }


    private Phrase mapper(CSVRecord record, String[] headers) {
        Phrase phrase = new Phrase();

        for (String header : headers) {
            setProperty(phrase, header, record.get(header));
        }

        return phrase;
    }

    private void setProperty(Phrase phrase, String header, String cellData) {
        try {
            BeanUtils.setProperty(phrase, header, cellData);
        } catch (IllegalAccessException e) {
            LOGGER.error("ERror while setting property " + header + " of Phrase", e);
        } catch (InvocationTargetException e) {
            LOGGER.error("Invocation ERror while setting property " + header + " of Phrase", e);
        }
    }
}
