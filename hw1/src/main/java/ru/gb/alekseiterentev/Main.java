package ru.gb.alekseiterentev;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Person person = new Person.PersonBuilder()
                .setFirstName("Aleksei")
                .setLastName("Terentev")
                .setMiddleName("John")
                .setCountry("Russia")
                .setAddress("Saratov, Lenina st., 10")
                .setPhone("01234567890")
                .setGender("male")
                .setAge(31)
                .build();

        System.out.println(person);

        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle());
        shapes.add(new Rectangle());
        shapes.add(new Square());

        shapes.forEach(Shape::draw);
    }
}
