package com.lemzki.tools.reader;


public interface ResourceReader<R> {

    /**
     * @return the read resource
     */
    R read(String fileName);
}
