package com.lemzki.tools.dev.resource.reader.impl;

import com.lemzki.tools.dev.enums.Resource;
import com.lemzki.tools.dev.resource.mapper.CSVResourceMapper;
import com.lemzki.tools.dev.resource.reader.ResourceReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public abstract class CSVResourceReader<T> implements ResourceReader<List<T>> {

    private static final Logger logger = LogManager.getLogger(CSVResourceReader.class);

    protected abstract CSVResourceMapper<T> mapper();

    public abstract Resource getResource();

    @Override
    public List<T> read() {
        List<T> rows = new ArrayList<>();
        try {

            File file = new ClassPathResource(getResource().getFileName()).getFile();

            Reader in = new FileReader(file);


            List<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in).getRecords();

            rows = records.stream()
                    .map(mapper()::map)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            logger.error("Something went wrong while reading file: " + getResource().getFileName(), e);
        }

        return rows;
    }
}
