package ru.otus.SpringIntegrationProject.service;

import org.springframework.stereotype.Component;
import ru.otus.SpringIntegrationProject.domain.Person;

@Component
public class ManicureServiceImpl implements ManicureService {

    public Person makeManicure(Person person) {
        System.out.println("Doing manicure for " + person.getName());
        person.setNotManicured(false);
        System.out.println(person.getName() + " got manicured");
        return person;
    }

    public void varnishNails(Person person) {
        System.out.println("Varnishing " + person.getName() + "'s nails");
        System.out.println("Finished " + person.getName() + "'s manicure");
        person.setClientDebt(person.getClientDebt() + 100);
    }
}
