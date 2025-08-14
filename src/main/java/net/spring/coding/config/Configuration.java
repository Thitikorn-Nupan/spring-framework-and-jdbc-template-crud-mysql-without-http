package net.spring.coding.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@org.springframework.context.annotation.Configuration
/** @ComponentScan tells spring container looking at this folder */
@ComponentScan(basePackages="net.spring.coding")
/** @PropertySource using to include properties file */
@PropertySource("classpath:information/source.properties")
public class Configuration {
    private DriverManagerDataSource dataSource;
    /** using @Value() to retrieve value from properties file */
    @Value("${jdbc.driver}") private String driver;
    @Value("${jdbc.url}") private String url;
    @Value("${jdbc.username}") private String username;
    @Value("${jdbc.password}") private String password;

    @Bean
    public DriverManagerDataSource dataSource () {
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource ;
    }
}
