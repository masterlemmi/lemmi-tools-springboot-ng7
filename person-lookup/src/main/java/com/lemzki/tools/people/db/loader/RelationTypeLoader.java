package com.lemzki.tools.people.db.loader;

import com.lemzki.tools.people.db.model.RelationType;
import com.lemzki.tools.people.db.repository.RelationTypeRepository;
import org.apache.commons.csv.CSVFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

@Component
public class RelationTypeLoader {

    @Autowired
    RelationTypeRepository relationTypeRepository;

    public void loadRelationTypes() {
        try {

            File file = new ClassPathResource("relationtypes.csv").getFile();

            Reader in = new FileReader(file);


            CSVFormat.RFC4180.parse(in).forEach(
                    record -> relationTypeRepository.save(new RelationType(record.get(0)))
            );

        } catch (Exception e) {
            System.out.println(e);

            System.out.println("ERROR WITH RELATIONS LOADER");
        }
    }
}
