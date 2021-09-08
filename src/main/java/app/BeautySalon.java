package app;

import domain.Person;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface BeautySalon {

    @Gateway(requestChannel = "beautySalonFlow.input", replyChannel = "clientOut")
    Person process(Person person);



}
