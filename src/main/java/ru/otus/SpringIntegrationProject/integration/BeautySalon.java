package ru.otus.SpringIntegrationProject.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.SpringIntegrationProject.domain.Person;

@MessagingGateway
public interface BeautySalon {

    @Gateway(requestChannel = "clientIn", replyChannel = "clientOut")
    Person process(Person person);

    @Gateway(requestChannel = "manicureChannel")
    Person processManicure(Person person);

//    @Gateway(requestChannel = "MakeUpClient.input")
//    void processMakeUp(Person person);

}
