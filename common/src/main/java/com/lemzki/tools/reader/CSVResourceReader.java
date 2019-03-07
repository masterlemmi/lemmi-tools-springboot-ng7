package com.lemzki.tools.reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.util.List;

@Service
public class CSVResourceReader implements ResourceReader<CsvData> {

    private static final Logger logger = LogManager.getLogger(CSVResourceReader.class);


    @Override
    public CsvData read(String filename) {

        try {

            File file = new ClassPathResource(filename).getFile();

            Reader in = new FileReader(file);

            CSVFormat format = CSVFormat.RFC4180.withFirstRecordAsHeader();

            String[] headers =format.getHeader();
            List<CSVRecord> records = format.parse(in).getRecords();

            return new CsvData(headers, records);

        } catch (FileNotFoundException e) {
            logger.error("File was not found: " + filename, e);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Resource Not Found", e);
        } catch (IOException e) {
            logger.error("Something went wrong wile retreiving: " + filename, e);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Something with wrong with the resource", e);
        }
    }
}
