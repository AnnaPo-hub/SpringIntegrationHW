package ru.otus.SpringIntegrationProject.service;

import org.springframework.stereotype.Component;
import ru.otus.SpringIntegrationProject.domain.Person;

@Component
public class PaymentServiceImpl implements PaymentService {
    @Override
    public Person checkClientDebt(Person person) {
        System.out.println(person.getName() + " should pay " + person.getClientDebt() + " euros");
        return person;
    }
}
