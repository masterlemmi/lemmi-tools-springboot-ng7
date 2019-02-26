package com.lemzki.tools.dev.shortcuts;



import com.lemzki.tools.reader.CSVResourceReader;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Service
class IdeShortcutServiceImpl implements IdeShortcutService {

    private static final String FILE_NAME = "plugins.csv";

    @Autowired
    CSVResourceReader resourceReader;

    private Function<CSVRecord, IdeShortcut> ideshortcutMapper = (record) -> {
        return new IdeShortcut();
    };

    @Override
    public List<IdeShortcut> getAllShortcuts() {
        return resourceReader.read(FILE_NAME).stream()
                .map(ideshortcutMapper)
                .collect(toList());
    }
}
