package ru.otus.SpringIntegrationProject.service;

import org.springframework.stereotype.Service;
import ru.otus.SpringIntegrationProject.domain.Person;

@Service
public class Payment {

    public Person checkClientDebt(Person person) {
        System.out.println(person.getName() + " should pay " + person.getClientDebt() + " euros");
        return person;
    }
}
