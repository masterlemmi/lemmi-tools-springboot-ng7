package com.lemzki.tools;


import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.ListConnectionsResponse;
import com.google.api.services.people.v1.model.Person;
import com.lemzki.tools.people.db.loader.PersonLoader;
import com.lemzki.tools.people.db.repository.GContactRepository;
import com.lemzki.tools.security.LTUser;
import com.lemzki.tools.security.exception.UnauthenticatedSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.util.List;

@SpringBootApplication(scanBasePackages = "com.lemzki.tools")
@RestController
@EnableOAuth2Sso
public class LemzkiToolsApplication  extends WebSecurityConfigurerAdapter {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //registry.addMapping("/greeting-javaconfig").allowedOrigins("http://localhost:9000");
                registry.addMapping("/**");
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(LemzkiToolsApplication.class, args);
    }

    @Bean
    ApplicationRunner init(PersonLoader personLoader) {
        return args -> {
            personLoader.loadPersons();
        };
    }




@Autowired
GContactRepository repo;

    @Autowired
    GoogleIdTokenVerifier googleIdTokenVerifier;

    @GetMapping("/user")
    public ResponseEntity<LTUser> user(Principal principal) {
       if (principal == null){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
       } else {
           return ResponseEntity.ok(LTUser.from(principal));
       }
    }

    @GetMapping("/test")
    public void test(Authentication authentication) throws GeneralSecurityException, IOException {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
       String accessToken =details.getTokenValue();


        GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        PeopleService peopleService = new PeopleService.Builder(GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(), credential)
                .setApplicationName("LemmiTools")
                .build();

        ListConnectionsResponse response = peopleService.people().connections().list(ME)
                .setPersonFields(FIELDS)
                .execute();
        System.out.println(response);
        List<Person> contactList = response.getConnections();




    }

    //OAuth2AuthenticationToken
//DefaultOidcUser,


  @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
                .authorizeRequests()
                    .antMatchers("/")
                    .permitAll()
                    .and()
                .authorizeRequests()
                    .antMatchers("/h2/**")
                    .permitAll()
                    .and()
                .authorizeRequests()
                .antMatchers("/", "/login**", "/webjars/**", "/error**", "/g_signin**", "/user**")
                     .permitAll()
                .anyRequest()
                    .authenticated()
                    .and()
                .logout()
                    .logoutSuccessUrl("/")
                    .permitAll();

        //.and()
           //     .csrf()
            //        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

      http.csrf().disable();
        http.headers().frameOptions().disable();

    }


    private final static String ME="people/me";
    private final static String FIELDS ="urls,photos,phoneNumbers,userDefined,names,relations," +
            "nicknames,birthdays,coverPhotos,emailAddresses,genders";
}

