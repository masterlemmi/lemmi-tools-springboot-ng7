package com.lemzki.tools.reader;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Holder for List of records parse from file
 * and the header. To be used for reflectio nwhen
 * the headers match the name of the properties
 */
@Getter
@ToString
@AllArgsConstructor
public class CsvData {
    private String[] headers;
    private List<CSVRecord> records = new ArrayList<>();

}
