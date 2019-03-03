package com.lemzki.tools.security;

import com.lemzki.tools.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.util.Map;

@EnableOAuth2Client
@Configuration

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    OAuth2ClientContext oauth2ClientContext;

    @Autowired
    UserService userService;

    @Autowired
    PrincipalExtractor principalExtractor;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/h2/**").permitAll()
                    .antMatchers("/", "/login**", "/webjars/**", "/error**", "/g_signin**", "/insert**").permitAll()
                    .antMatchers("/ide/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                    .and()
               .logout()
                    .logoutSuccessUrl("/").permitAll()
                    .and()
               .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);



        //allow h2 console
        //.and()
        //     .csrf()
        //        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());



        //allow h2 console
        http.csrf().disable();
        http.headers().frameOptions().disable();

    }



    private Filter ssoFilter() {
        OAuth2ClientAuthenticationProcessingFilter googleFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/google");
        OAuth2RestTemplate googleTemplate = new OAuth2RestTemplate(google(), oauth2ClientContext);
        googleFilter.setRestTemplate(googleTemplate);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(googleResource().getUserInfoUri(), google().getClientId());
        tokenServices.setRestTemplate(googleTemplate);
        tokenServices.setPrincipalExtractor(principalExtractor);
        googleFilter.setTokenServices(tokenServices);
        googleFilter.setApplicationEventPublisher(applicationEventPublisher);
        return googleFilter;
    }

    @Bean
    @ConfigurationProperties("google.client")
    public AuthorizationCodeResourceDetails google() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties("google.resource")
    public ResourceServerProperties googleResource() {
        return new ResourceServerProperties();
    }

    @Bean
    public FilterRegistrationBean<OAuth2ClientContextFilter> oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean<OAuth2ClientContextFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }


    //allow h2 console
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

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public LoggedInUser loggedInUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth instanceof OAuth2Authentication){
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) auth;

            String googleId = ((Map<String, String>) oAuth2Authentication.getUserAuthentication().getDetails()).get("id");

            return () ->  userService.findByGoogleId(googleId).orElse(User.ANONYMOUS);
        }

        System.out.println("Logged in anonymously or wiht something else" + auth);
        return LoggedInUser.ANONYMOUS;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
