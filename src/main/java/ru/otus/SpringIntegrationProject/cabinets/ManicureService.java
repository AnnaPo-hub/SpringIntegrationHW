package ru.otus.SpringIntegrationProject.cabinets;

import ru.otus.SpringIntegrationProject.domain.Person;
import org.springframework.stereotype.Component;

@Component
public class ManicureService {

    public Person makeManicure(Person person) {
        System.out.println("Doing manicure for " + person.getName());
        person.setNotManicured(false);
        System.out.println(person.getName() + " got manicured");
        return person;
    }

    public Person varnishNails(Person person) {
        System.out.println("Varnishing " + person.getName()+ "'s nails");
        System.out.println("Finished "+ person.getName() + "'s manicure");
        return person;
    }
}
