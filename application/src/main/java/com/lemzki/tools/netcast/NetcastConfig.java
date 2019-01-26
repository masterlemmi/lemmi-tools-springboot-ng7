package com.lemzki.tools.netcast;

import com.lemzki.tools.people.db.dto.builder.PersonBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class NetcastConfig {

    private static final String HIBERNATE_HBM2DDL_AUTO = "validate";
    private static final String HIBERNATE_DIALECT = "org.hibernate.dialect.PostgreSQLDialect";


    @Bean
    @ConfigurationProperties(prefix = "netcast.datasource")
    public DataSource netcastDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean netcastEntityManager(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(netcastDataSource())
                .packages("com.lemzki.tools.netcast.model")
                .persistenceUnit("netcast")
                .properties(properties())
                .build();
    }

    @Bean
    public PlatformTransactionManager netcastTransactionManager(@Qualifier("netcastEntityManager") EntityManagerFactory netcastEntityManager) {
        return new JpaTransactionManager(netcastEntityManager);
    }

    private Map<String, Object> properties() {
        Map<String, Object> propeprties = new HashMap<>();
        propeprties.put("hibernate.dialect", HIBERNATE_DIALECT);
        propeprties.put("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
        return propeprties;
    }

}

