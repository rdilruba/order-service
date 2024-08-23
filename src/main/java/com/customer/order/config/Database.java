package com.customer.order.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class Database {

    @Value("${aws.secrets.manager.db.secret}")
    private String secretName;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Autowired
    private SecretManager secretsManagerService;

    @Bean
    public DataSource dataSource() {
        SecretManager.DbSecret secret = secretsManagerService.getSecret(secretName);
        return org.springframework.boot.jdbc.DataSourceBuilder.create()
                .url(dbUrl)
                .username(secret.username)
                .password(secret.password)
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }
}
