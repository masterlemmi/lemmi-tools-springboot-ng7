package com.lemzki.tools.dev.resource.mapper;

import org.apache.commons.csv.CSVRecord;

@FunctionalInterface
public interface CSVResourceMapper<T> {

    T map (CSVRecord record);
}
