package com.lemzki.tools.dev.resource.reader.impl;

import com.lemzki.tools.dev.enums.Resource;
import com.lemzki.tools.dev.initializer.InitializerService;
import com.lemzki.tools.dev.resource.reader.InMemoryResourceReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class InMemoryResourceReaderImpl implements InMemoryResourceReader {

    Map<Resource, ?> map = new HashMap<>();

    private static final Logger logger = LogManager.getLogger(InMemoryResourceReaderImpl.class);

    @Autowired
    private InitializerService initializerService;


    @Override
    public <T> T getResource(Resource name) {
        if (map.get(name) == null) {
            map.put(name, initializerService.initResource(name));
        }

        T data = null;

        try {
            data = (T) map.get(name);
        } catch (Exception e) {
            logger.error("Unable to cast retrieved data to type T");
        }

        return data;

    }


}
