package ru.otus.SpringIntegrationProject;

import ru.otus.SpringIntegrationProject.domain.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;

@IntegrationComponentScan
@SuppressWarnings({"resource", "Duplicates"})
@Configuration
@EnableIntegration
@ComponentScan("ru/otus/SpringIntegrationProject/cabinets")
@SpringBootApplication
public class App {

    @Bean
    DirectChannel clientOut() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow beautySalonFlow() {
        return flow -> flow
                .filter(person -> ((Person) person).isNotManicured())
                .gateway(manicureFlow())
                .filter(person -> ((Person) person).isWithoutMakeUp())
                .handle("makeUpService", "toDoMakeUp")
                .channel("clientOut");
    }

    @Bean
    public IntegrationFlow manicureFlow() {
        return flow -> flow
                .handle("manicureService", "makeManicure")
                .handle("manicureService", "varnishNails");
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        ConfigurableApplicationContext ctx =
                SpringApplication.run(App.class, args);
        DirectChannel outputChannel = ctx.getBean("clientOut", DirectChannel.class);
        outputChannel.subscribe((x -> System.out.println(x)));

        ctx.getBean(BeautySalon.class)
                .process(new Person("Anna", true, true));
        ctx.close();
    }
}

