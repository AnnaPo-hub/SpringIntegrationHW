package ru.otus.SpringIntegrationProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import ru.otus.SpringIntegrationProject.domain.Person;
import ru.otus.SpringIntegrationProject.integration.BeautySalon;

@IntegrationComponentScan
@SuppressWarnings({"resource", "Duplicates"})
@Configuration
@EnableIntegration
@ComponentScan("ru/otus/SpringIntegrationProject")
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(App.class, args);

        ctx.getBean(BeautySalon.class)
                .process(new Person("Anna", true, false,0));
        ctx.close();
    }
}

