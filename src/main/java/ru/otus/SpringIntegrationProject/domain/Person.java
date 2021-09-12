package ru.otus.SpringIntegrationProject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class Person {
    private final String name;
    private boolean notManicured;
    private boolean withoutMakeUp;
    private int clientDebt;

//    public static Person of (String name, boolean notManicured, boolean withoutMakeUp, int value){
////        return new Person (name, notManicured, withoutMakeUp, value);
//    }
}
