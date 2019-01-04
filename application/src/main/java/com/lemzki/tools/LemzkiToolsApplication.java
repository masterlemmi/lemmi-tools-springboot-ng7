package com.lemzki.tools;


import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.lemzki.tools.people.db.loader.PersonLoader;
import com.lemzki.tools.people.db.repository.GContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Principal;

@SpringBootApplication(scanBasePackages = "com.lemzki.tools")
@RestController
public class LemzkiToolsApplication  {

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

    @Autowired
    private DataSource dataSource;


    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }





    @GetMapping("/test")
    private void test(Authentication authentication) throws GeneralSecurityException, IOException {
        DefaultOidcUser user = (DefaultOidcUser) authentication.getPrincipal();
        String accessToken = user.getIdToken().getTokenValue();

        GoogleIdToken googleIdToken = googleIdTokenVerifier.verify(accessToken);

        repo.testGet(accessToken);
    }

    //OAuth2AuthenticationToken
//DefaultOidcUser,


   // @Override
    protected void configure(HttpSecurity http) throws Exception {
    /*    http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2Login();

        http.antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**", "/webjars/**", "/error**")
                     .permitAll()
                .anyRequest()
                    .authenticated()
                .and().logout().logoutSuccessUrl("/").permitAll();
        //             .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());*/
    }

}