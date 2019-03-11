package com.lemzki.tools.dev.plugins;


import com.lemzki.tools.dev.Ide;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
class IdePluginServiceImpl implements IdePluginService {

    private static final String FILE_NAME = "ide_plugins.csv";

//    @Autowired
//    CSVResourceReader resourceReader;

    @Autowired
    IdePluginRepository repository;

    private Function<CSVRecord, IdePlugin> idePluginMapper = (record) -> {
        IdePlugin plugin =  new IdePlugin();
        plugin.setLink(record.get("link"));
        plugin.setIde(Ide.valueOf(record.get("ide")));
        plugin.setName(record.get("name"));
        return plugin;
    };

    @Override
    public List<IdePlugin> getAllPlugins() {
        return repository.findAll();

    }
}
