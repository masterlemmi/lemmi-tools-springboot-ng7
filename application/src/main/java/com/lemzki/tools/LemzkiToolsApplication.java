package com.lemzki.tools;


import com.lemzki.tools.interests.finance.debts.Debt;
import com.lemzki.tools.interests.finance.debts.DebtRepository;
import com.lemzki.tools.interests.finance.debts.DebtService;
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
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@SpringBootApplication(scanBasePackages = "com.lemzki.tools") @RestController @EnableAsync @EnableOAuth2Client @EnableAuthorizationServer
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
            //
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


    @Autowired DebtRepository debtRepository;
    @GetMapping("/doIt")
    public Collection doIt(){
        LocalDate from = LocalDate.of(2018,12,1);
        LocalDate to = LocalDate.of(2019,1,1);
        Collection<Debt> debts =  debtRepository.findDebt("RCBC_VISA", from, to);
        return debts;
    }



}

