package ru.otus.SpringIntegrationProject.service;

import org.springframework.stereotype.Service;
import ru.otus.SpringIntegrationProject.domain.Person;

@Service
public interface ManicureService {

    Person makeManicure(Person person);

    void varnishNails(Person person);
}
