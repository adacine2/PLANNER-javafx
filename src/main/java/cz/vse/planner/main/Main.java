package cz.vse.planner.main;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@ComponentScan(basePackages = {"cz.vse.planner"})
@EnableJpaRepositories(basePackages = {"cz.vse.planner"})
@EntityScan(basePackages = "cz.vse.planner.entity")
public class Main {
    private static ConfigurableApplicationContext springContext;

    public static ConfigurableApplicationContext getSpringContext() {
        return springContext;
    }

    public static void main(String[] args) {
        springContext = new SpringApplicationBuilder(Main.class).run(args);
        JavaFxApp javaFxApp = springContext.getBean(JavaFxApp.class);
        Application.launch(JavaFxApp.class, args);
    }
}
