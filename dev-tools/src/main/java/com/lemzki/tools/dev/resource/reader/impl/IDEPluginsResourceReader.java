package com.lemzki.tools.dev.resource.reader.impl;

import com.lemzki.tools.dev.enums.Resource;
import com.lemzki.tools.dev.model.IDEPlugin;
import com.lemzki.tools.dev.resource.mapper.CSVResourceMapper;
import org.springframework.stereotype.Component;

@Component
public class IDEPluginsResourceReader extends CSVResourceReader<IDEPlugin> {

    private CSVResourceMapper<IDEPlugin> idePluginMapper = (record -> {
        return new IDEPlugin();
    });

    @Override
    protected CSVResourceMapper<IDEPlugin> mapper() {
        return idePluginMapper;
    }

    @Override
    public Resource getResource() {
        return Resource.PLUGINS;
    }


}
