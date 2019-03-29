package com.lemzki.tools;


import com.google.common.collect.Sets;
import com.lemzki.tools.interests.finance.debts.Debt;
import com.lemzki.tools.interests.finance.debts.DebtRepository;
import com.lemzki.tools.interests.finance.debts.Due;
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
import java.util.ArrayList;
import java.util.Collection;

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
        Debt debt = debtRepository.findByName("BPIa");


        LocalDate firstDate = LocalDate.of(2018,4,1);
        for (int i = 0; i<dues.length; i++){
            Due due = new Due();
            due.setDebt(debt);
            due.setAmount(dues[i]);
            due.setDate(firstDate);
            firstDate = firstDate.plusMonths(1);
            debt.addDue(due);
        }

//         debtRepository.save(debt);
        return null;
    }

    double[] dues = {58781.77, 56660.08, 51814.72, 58534.22, 59520.11, 57528.64, 53817.46, 56627.68, 59707.29, 67539.30, 75263.59, 75347.81};


}

