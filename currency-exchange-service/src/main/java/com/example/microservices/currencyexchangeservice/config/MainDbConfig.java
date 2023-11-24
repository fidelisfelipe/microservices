package com.example.microservices.currencyexchangeservice.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.microservices.currencyexchangeservice.repository.app",
        entityManagerFactoryRef = "appEntityManagerFactory",
        transactionManagerRef = "appTransactionManager"
)
public class MainDbConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource appDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "appEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean appEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("appDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = builder
                .dataSource(dataSource)
                .packages("com.example.microservices.currencyexchangeservice.model.app") // Seu pacote de entidades principais
                .persistenceUnit("appDS")
                .build();
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", "create");
        jpaProperties.put("hibernate.show_sql", "true");
        jpaProperties.put("hibernate.format_sql", "true");
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

        emf.setJpaProperties(jpaProperties);
        return emf;
    }

    @Primary
    @Bean(name = "appTransactionManager")
    public PlatformTransactionManager appTransactionManager(
            @Qualifier("appEntityManagerFactory") EntityManagerFactory appEntityManagerFactory) {
        return new JpaTransactionManager(appEntityManagerFactory);
    }
}
