package cabinets;

import domain.Person;

public class ManicureService {
    public Person makeManicure(Person person) {
        System.out.println("Doing manicure for " + person.getName());
        person.setManicured(true);
        System.out.println(person.getName() + "got manicured");
        return person;
    }
}
