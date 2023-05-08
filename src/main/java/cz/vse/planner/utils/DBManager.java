package cz.vse.planner.utils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DBManager {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://bksjqhuq7n0zoldegy1x-mysql.services.clever-cloud.com:3306/bksjqhuq7n0zoldegy1x");
        dataSource.setUsername("uzmd8ltc64idr46o");
        dataSource.setPassword("oP2AY4kVV6IyAGWKR3KO");
        return dataSource;
    }
}