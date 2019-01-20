package com.lemzki.tools.dev.resource.reader;

import com.lemzki.tools.dev.enums.Resource;


public interface InMemoryResourceReader {

    <T> T getResource(Resource name);
}
