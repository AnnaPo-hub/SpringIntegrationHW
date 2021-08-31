package cabinets;

import domain.Person;

public class MakeUpService {
    public Person toDoMakeUp(Person person) {
        System.out.println("Doing makeup for " + person.getName());
        person.setWithMakeUp(true);
        System.out.println(person.getName() + "got makeup");
        return person;
    }
}
