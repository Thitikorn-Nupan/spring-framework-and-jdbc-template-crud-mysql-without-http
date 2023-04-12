package net.spring.coding.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@org.springframework.context.annotation.Configuration
@ComponentScan(basePackages="net.spring.coding") /* tells spring container looking at this folder */
@PropertySource("classpath:informations/information.properties") /* @PropertySource("classpath:") using to include properties file */
public class Configuration {
    private DriverManagerDataSource dataSource;
    @Value("${jdbc.driver}") private String driver;
    @Value("${jdbc.url}") private String url;
    @Value("${jdbc.username}") private String username;
    @Value("${jdbc.password}") private String password;

    /* using @Value() to retrieve value from properties file */
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
