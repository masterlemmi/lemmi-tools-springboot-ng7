package com.lemzki.tools.dev.initializer.impl;

import com.lemzki.tools.dev.enums.Resource;
import com.lemzki.tools.dev.initializer.InitializerService;
import com.lemzki.tools.dev.resource.reader.ResourceReader;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Service
public class InitializerServiceImpl implements InitializerService {
    private Map<Resource, ResourceReader> resourceReaders;

    public InitializerServiceImpl(List<ResourceReader> readers) {
        resourceReaders = readers.stream().collect(toMap(ResourceReader::getResource, Function.identity()));
    }

    @Override
    public <T> T initResource(Resource resource) {
        ResourceReader<T> reader = resourceReaders.get(resource);
        return reader.read();
    }


}