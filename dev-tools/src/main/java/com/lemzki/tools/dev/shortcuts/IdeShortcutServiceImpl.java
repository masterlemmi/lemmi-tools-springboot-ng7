package com.lemzki.tools.dev.shortcuts;



import com.lemzki.tools.reader.CSVResourceReader;
import com.lemzki.tools.reader.CsvData;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Service
class IdeShortcutServiceImpl implements IdeShortcutService {

    private static final String FILE_NAME = "ide_shortcuts.csv";

    @Autowired
    CSVResourceReader resourceReader;

    @Autowired
    IdeShortcutRepository repository;



    @Override
    public List<IdeShortcut> getAllShortcuts() {
        return repository.findAll();
    }
}
