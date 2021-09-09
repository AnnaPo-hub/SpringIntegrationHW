package ru.otus.SpringIntegrationProject.cabinets;

import ru.otus.SpringIntegrationProject.domain.Person;
import org.springframework.stereotype.Service;

@Service
public class MakeUpService {
    public Person toDoMakeUp(Person person) {
        System.out.println("Doing makeup for " + person.getName());
        person.setWithoutMakeUp(false);
        System.out.println(person.getName() + " got makeup");
        return person;
    }
}
