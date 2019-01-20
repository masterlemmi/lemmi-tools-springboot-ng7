package com.lemzki.tools.dev.resource.reader.impl;

import com.lemzki.tools.dev.enums.Resource;
import com.lemzki.tools.dev.resource.reader.ResourceReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class StringResourceReader implements ResourceReader<String> {

    private static final Logger logger = LogManager.getLogger(StringResourceReader.class);

    @Override
    public Resource getResource() {
        return Resource.SOMETEXT;
    }

    @Override
    public String read() {
        return "I read file " + getResource().getFileName() + "  and return this";
    }
}
