package com.demo.boot4;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Person {
    private String zone;
    private String nom;
    private int age;
}


//public record Person(String zone, String nom, int age) {
//    public Person {
//        if (age < 0) {
//            throw new IllegalArgumentException("age < 0");
//        }
//    }
//}