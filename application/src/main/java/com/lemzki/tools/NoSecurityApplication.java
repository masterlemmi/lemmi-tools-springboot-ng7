/*
package com.lemzki.tools;


import com.lemzki.tools.people.db.loader.PersonLoader;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication(scanBasePackages = "com.lemzki.tools")
@RestController

public class NoSecurityApplication  {

    public static void main(String[] args) {
        SpringApplication.run(NoSecurityApplication.class, args);
    }

    @Bean
    ApplicationRunner init(PersonLoader personLoader) {
        return args -> {
            personLoader.loadPersons();
        };
    }


}

*/
