package cabinets;

import domain.Person;
import org.springframework.stereotype.Component;

@Component
public class ManicureService {

    public Person makeManicure(Person person) {
        System.out.println("Doing manicure for " + person.getName());
        person.setNotManicured(false);
        System.out.println(person.getName() + " got manicured");
        return person;
    }
}
