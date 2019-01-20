package com.lemzki.tools.dev.resource.reader.impl;

import com.lemzki.tools.dev.enums.Resource;
import com.lemzki.tools.dev.model.IDEShortcut;
import com.lemzki.tools.dev.resource.mapper.CSVResourceMapper;
import org.springframework.stereotype.Component;

@Component
public class IDEShorcutResourceReader extends CSVResourceReader<IDEShortcut> {

    private CSVResourceMapper<IDEShortcut> idePluginMapper = (record -> {
        return new IDEShortcut();
    });

    @Override
    protected CSVResourceMapper<IDEShortcut> mapper() {
        return idePluginMapper;
    }

    @Override
    public Resource getResource() {
        return Resource.SHORTCUTS;
    }


}
