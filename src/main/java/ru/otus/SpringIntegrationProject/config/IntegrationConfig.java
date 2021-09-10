package ru.otus.SpringIntegrationProject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import ru.otus.SpringIntegrationProject.domain.Person;

@Configuration
@RequiredArgsConstructor
public class IntegrationConfig {

    @Bean
    IntegrationFlow process() {
        return flow -> flow
                .<Person, Boolean>route(Person::isNotManicured,
                        m -> m
                                //.subFlowMapping(true, processManicure()))
                                .subFlowMapping(true, sf -> sf.handle("manicureService", "makeManicure")))
                //  .subFlowMapping(false, sf -> sf.handle("")))
                // .channel(this.afterManicureChannel())
                .<Person, Boolean>route(person -> person.isWithoutMakeUp(),
                        m -> m
                                .subFlowMapping(true, sf -> sf.handle("makeUpService", "toDoMakeUp")))
                .handle("payment", "checkClientDebt")
                .channel(clientOut());
        //  .subFlowMapping(false, sf -> sf.handle("")); ;


//                .routeToRecipients(route -> route
//                .recipient("manicureChannel",
//                        this::isNotManicured)
//                        .defaultOutputToParentFlow()
//                .recipient("makeUpChannel",
//                        this::isWithoutMakeUp)
//                        .defaultOutputToParentFlow())
//                .channel(paymentChannel());
    }

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

    //
//    @Bean
//    QueueChannel makeUpChannel() {
//        return new QueueChannel();
//    }
//
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
//
//    @Bean
//    public IntegrationFlow beautySalonFlow() {
//        return IntegrationFlows.from(clientIn())
//                .gateway(processManicure())
//                .channel(this.afterManicureChannel())
//                .filter(person -> ((Person) person).isWithoutMakeUp())
//                .handle("makeUpService", "toDoMakeUp")
//               // .handle("payment", "checkClientDebt")
//                .channel(clientOut())
//                .get();
//    }

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
