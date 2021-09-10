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
    IntegrationFlow mainFlow() {
        return IntegrationFlows.from("clientIn")
                .routeToRecipients(route -> route
                        .recipientFlow(this::isNotManicured, processManicure())
                        .recipientFlow(this::isWithoutMakeUp, processMakeUp())
                        .recipientFlow(processPayment())
                        .defaultOutputToParentFlow())
                .channel(clientOut())
                .get();
    }

    @Bean
    public DirectChannel clientOut() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow processManicure() {
        return flow -> flow
                .handle("manicureService", "makeManicure")
                .handle("manicureService", "varnishNails");
    }

    @Bean
    public IntegrationFlow processMakeUp() {
        return flow -> flow
                .handle("makeUpService", "toDoMakeUp");
    }

    @Bean
    public IntegrationFlow processPayment() {
        return flow -> flow
                .handle("payment", "checkClientDebt");
    }

    boolean isWithoutMakeUp(Person person) {
        return person.isWithoutMakeUp();
    }

    boolean isNotManicured(Person person) {
        return person.isNotManicured();
    }
}
