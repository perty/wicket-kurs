package se.crisp.example.quickly.core;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonService extends JpaRepository<Person, Long> {
    List<Person> findByFirstNameAndLastName(String firstName, String lastName);
}