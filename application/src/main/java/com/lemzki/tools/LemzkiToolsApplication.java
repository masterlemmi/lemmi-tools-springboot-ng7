package com.lemzki.tools;


import com.lemzki.tools.people.db.google.service.impl.PeopleAPIBatchUpdaterImpl;
import com.lemzki.tools.people.db.loader.PersonLoader;
import com.lemzki.tools.people.db.google.service.PeopleAPIService;
import com.lemzki.tools.people.db.model.PersonDb;
import com.lemzki.tools.people.db.service.PersonService;
import com.lemzki.tools.security.LoggedInUser;
import com.lemzki.tools.security.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@SpringBootApplication(scanBasePackages = "com.lemzki.tools")
@RestController
@EnableAsync
public class LemzkiToolsApplication {

    private static final Logger logger = LogManager.getLogger(LemzkiToolsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LemzkiToolsApplication.class, args);
    }

    @Bean
    ApplicationRunner init(PersonLoader personLoader) {
        return args -> {
            logger.info("STARTED INIT RUNNER");
            logger.debug("STARTED INIT RUMMER DBEUG");
            logger.error("STARTED INIT RUMMER ERROR");
            logger.warn("STARTED INIT RUMMER warn");
            //personLoader.loadPersons();
        };
    }


    @Autowired
    LoggedInUser loggedInUser;


    @GetMapping("/user")
    public ResponseEntity<User> user() {
        User user = loggedInUser.get();

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @Autowired
    PeopleAPIService apiService;

    @GetMapping("/testImport")
    public String testImport() {
        return apiService.importContactPersonsFromGoogle();
    }

    @GetMapping("/testExportAll")
    public String testEport() throws InterruptedException {
        return apiService.exportAllContactsToGoogle();
    }

    @Autowired
    PersonService personService;
    @GetMapping("/testExportOne")
    public String testEportOne() throws InterruptedException, ExecutionException {
        int id = 23;
        PersonDb personDb = personService.find(23).get();
        return apiService.exportOneContactToGoogle(personDb);
    }

    @Autowired
    PeopleAPIBatchUpdaterImpl updater;

    @GetMapping("/testLog")
    public String readLog() {
        return updater.getLogs();
    }


}

