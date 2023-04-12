package net.spring.coding.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@org.springframework.context.annotation.Configuration
@ComponentScan(basePackages="net.spring.coding") /* tells spring container looking at this folder */
public class Configuration {
    private DriverManagerDataSource dataSource;
    @Bean
    public DriverManagerDataSource dataSource () {
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3308/customer");
        dataSource.setUsername("root-np");
        dataSource.setPassword("642/321Architecture?222");
        return dataSource ;
    }
}
