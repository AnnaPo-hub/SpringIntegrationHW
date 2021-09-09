package ru.otus.SpringIntegrationProject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import ru.otus.SpringIntegrationProject.domain.Person;

@Configuration
@RequiredArgsConstructor
public class IntegrationConfig {

    @Bean
    public DirectChannel clientIn() {
        return new DirectChannel();
    }

    @Bean
    public DirectChannel clientOut() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow beautySalonFlow() {
        return IntegrationFlows.from(clientIn())
                .filter(person -> ((Person) person).isNotManicured())
                .gateway(manicureFlow())
                .filter(person -> ((Person) person).isWithoutMakeUp())
                .handle("makeUpService", "toDoMakeUp")
                .handle("payment", "checkClientDebt")
                .channel(clientOut())
                .get();
    }

    @Bean
    public IntegrationFlow manicureFlow() {
        return flow -> flow
                .handle("manicureService", "makeManicure")
                .handle("manicureService", "varnishNails");
    }
}
