package com.lemzki.tools.dev.plugins;



import com.lemzki.tools.reader.CSVResourceReader;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Service
class IdePluginServiceImpl implements IdePluginService {

    private static final String FILE_NAME = "plugins.csv";

    @Autowired
    CSVResourceReader resourceReader;

    private Function<CSVRecord, IdePlugin> idePluginMapper = (record) -> {
        return new IdePlugin();
    };

    @Override
    public List<IdePlugin> getAllPlugins() {
        return resourceReader.read(FILE_NAME).stream()
                .map(idePluginMapper)
                .collect(toList());
    }
}
