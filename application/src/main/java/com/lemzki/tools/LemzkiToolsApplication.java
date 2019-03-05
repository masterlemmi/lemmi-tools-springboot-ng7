package com.lemzki.tools;


import com.lemzki.tools.people.db.loader.PersonLoader;
import com.lemzki.tools.security.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication(scanBasePackages = "com.lemzki.tools") @RestController @EnableAsync
public class LemzkiToolsApplication {

    private static final Logger LOGGER = LogManager.getLogger(LemzkiToolsApplication.class);
    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(LemzkiToolsApplication.class, args);
    }

    @Autowired LoggedInUser loggedInUser;

    @Bean ApplicationRunner init(PersonLoader personLoader) {
        return args -> {
            //personLoader.loadPersons();
            Map<String, Object> details = new HashMap<>();
            details.put("email", "LEm@lem");
            details.put("id", "LEmzki");
            details.put("name", "lemueltaeza");
            User user = UserMapper.mapFrom(details);
            user.addRole(Role.USER);
             userService.save(user);


        };
    }

    @GetMapping("/user") public ResponseEntity<User> user() {
        User user = loggedInUser.get();

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(user);
        }
    }
}

