package com.lemzki.tools;


import com.lemzki.tools.people.db.loader.PersonLoader;
import com.lemzki.tools.security.LoggedInUser;
import com.lemzki.tools.security.User;
import com.lemzki.tools.security.UserService;
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

@SpringBootApplication(scanBasePackages = "com.lemzki.tools") @RestController @EnableAsync
public class LemzkiToolsApplication {

    private static final Logger LOGGER = LogManager.getLogger(LemzkiToolsApplication.class);
    @Autowired private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(LemzkiToolsApplication.class, args);
    }

    @Autowired LoggedInUser loggedInUser;

    @Bean ApplicationRunner init(PersonLoader personLoader) {
        return args -> {
            //code to run on init
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


    @GetMapping("/logout_success") public ResponseEntity<String> logout() {
        String message = "You have successfully logged out.";
        return ResponseEntity.ok(message);
    }



}

