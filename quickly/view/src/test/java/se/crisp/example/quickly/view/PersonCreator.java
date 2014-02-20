package se.crisp.example.quickly.view;

import se.crisp.example.quickly.core.Person;

public class PersonCreator {
    public static final String FIRST_NAME = "some first";
    public static final String LAST_NAME = "some last";

    public static Person createSomePerson() {
        return Person.builder().firstName(FIRST_NAME).lastName(LAST_NAME).address("some address").zip("some zip").city("some city").build();
    }
}
