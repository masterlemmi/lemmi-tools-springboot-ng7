package com.lemzki.tools.reader;

import lombok.*;
import org.apache.commons.csv.CSVRecord;

import java.util.List;
import java.util.function.Function;
import java.util.stream.DoubleStream;

/**
 * Holder for List of records parse from file
 * and the header. To be used for reflectio nwhen
 * the headers match the name of the properties
 */
@Getter
@ToString
@AllArgsConstructor
public class CsvData<T> {
    private String[] headers;
    private List<CSVRecord> records;
    private Function<CSVRecord, T> mapper;

    public List<T> mapResults(Function<CsvData, List<T>> mapper) {
       return mapper.apply(this);
    }
}
