package ru.otus.SpringIntegrationProject.service;

import org.springframework.stereotype.Service;
import ru.otus.SpringIntegrationProject.domain.Person;

@Service
public class MakeUpService {
    public Person toDoMakeUp(Person person) {
        System.out.println("Doing makeup for " + person.getName());
        person.setWithoutMakeUp(false);
        System.out.println(person.getName() + " got makeup");
        person.setClientDebt(person.getClientDebt()+100);
        return person;
    }
}
