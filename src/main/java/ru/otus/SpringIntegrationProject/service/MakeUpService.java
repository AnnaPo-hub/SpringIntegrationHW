package ru.otus.SpringIntegrationProject.service;

import org.springframework.stereotype.Service;
import ru.otus.SpringIntegrationProject.domain.Person;

@Service
public interface MakeUpService {
   Person toDoMakeUp(Person person);
}
