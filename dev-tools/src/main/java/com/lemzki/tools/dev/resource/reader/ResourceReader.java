package com.lemzki.tools.dev.resource.reader;

import com.lemzki.tools.dev.enums.Resource;

public interface ResourceReader <R> {

    Resource getResource();

    R read();
}
