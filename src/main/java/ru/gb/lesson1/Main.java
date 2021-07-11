package ru.gb.lesson1;

public class Main {
    public static void main(String[] args) {
        Person p = Person.builder()
                .firstName("name1")
                .lastName("lastname1")
                .middleName("midd1")
                .address("moscow")
                .gender("f")
                .phone("345667")
                .build();

        System.out.println(p);
    }
}
