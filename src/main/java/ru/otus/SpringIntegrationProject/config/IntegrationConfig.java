package ru.otus.SpringIntegrationProject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import ru.otus.SpringIntegrationProject.domain.Person;

@Configuration
@RequiredArgsConstructor
public class IntegrationConfig {
    Person person;

    @Bean
    public DirectChannel clientIn() {
        return new DirectChannel();
    }

    @Bean
    public DirectChannel clientOut() {
        return new DirectChannel();
    }

    @Bean
    DirectChannel afterManicureChannel() {
        return new DirectChannel();
    }

    @Bean
    QueueChannel makeUpChannel() {
        return new QueueChannel();
    }

    @Bean
    QueueChannel paymentChannel() {
        return new QueueChannel();
    }

    boolean isWithoutMakeUp(Person person) {
        return person.isWithoutMakeUp();
    }

    boolean isNotManicured(Person person) {
        return person.isNotManicured();
    }

    @Bean
    public IntegrationFlow beautySalonFlow() {
        return IntegrationFlows.from(clientIn())
                .gateway(processManicure())
                .channel(this.afterManicureChannel())
                .filter(person -> ((Person) person).isWithoutMakeUp())
                .handle("makeUpService", "toDoMakeUp")
               // .handle("payment", "checkClientDebt")
                .channel(clientOut())
                .get();
    }

    @Bean
   // @Gateway(replyChannel = "afterManicureChannel")
    public IntegrationFlow processManicure() {
        return flow -> flow
                .filter(person -> ((Person) person).isNotManicured())
                .handle("manicureService", "makeManicure")
                .handle("manicureService", "varnishNails")
                .channel(afterManicureChannel());
    }
}
