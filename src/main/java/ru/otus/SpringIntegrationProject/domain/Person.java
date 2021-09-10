package ru.otus.SpringIntegrationProject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Person {
    private final String name;
    boolean notManicured;
    boolean withoutMakeUp;
    int clientDebt;
}
