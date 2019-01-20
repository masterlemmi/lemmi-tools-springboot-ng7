package com.lemzki.tools.dev;

import com.lemzki.tools.dev.enums.Resource;
import com.lemzki.tools.dev.resource.reader.InMemoryResourceReader;

import java.util.List;
import java.util.Map;

public class TestStart {

    InMemoryResourceReader inMemReader;

    public void test(){
       String result =  inMemReader.getResource(Resource.SHORTCUTS);

        List<Map<String, String>> result2 =  inMemReader.getResource(Resource.SHORTCUTS);
    }
}
