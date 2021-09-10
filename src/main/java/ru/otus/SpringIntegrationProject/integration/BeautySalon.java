package ru.otus.SpringIntegrationProject.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.SpringIntegrationProject.domain.Person;

@MessagingGateway
public interface BeautySalon {

    @Gateway(requestChannel = "clientIn", replyChannel = "clientOut")
    Person process(Person person);
}
