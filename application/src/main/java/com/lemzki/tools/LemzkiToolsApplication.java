package com.lemzki.tools;


import com.lemzki.tools.people.db.loader.PersonLoader;
import com.lemzki.tools.security.LoggedInUser;
import com.lemzki.tools.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "com.lemzki.tools")
@RestController
public class LemzkiToolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LemzkiToolsApplication.class, args);
    }

    @Bean
    ApplicationRunner init(PersonLoader personLoader) {
        return args -> personLoader.loadPersons();
    }


    @Autowired
    LoggedInUser loggedInUser;


    @GetMapping("/user")
    public ResponseEntity<User> user() {
        User user = loggedInUser.getUser();

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(user);
        }
    }


}

