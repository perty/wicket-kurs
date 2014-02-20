package se.crisp.example.quickly.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CoreConfiguration.class)
@Transactional
public class PersonServiceTest {

    private static final String FIRST_NAME = "some first";
    private static final String LAST_NAME = "some last";

    @Inject
    PersonService personService;

    @Test
    public void save_person_stores_in_database() throws Exception {
        Person person = personService.save(createSomePerson());
        assertNotNull(person.getId());
    }

    @Test
    public void find_person_by_name_in_database() throws Exception {
        Person person = createTwoIdenticalPersonsInDatabase();

        List<Person> persons = personService.findByFirstNameAndLastName(FIRST_NAME, LAST_NAME);
        assertEquals(2, persons.size());
        assertEquals(person, persons.get(0));
    }

    private Person createTwoIdenticalPersonsInDatabase() {
        personService.save(createSomePerson());
        return personService.save(createSomePerson());
    }

    private Person createSomePerson() {
        return Person.builder().firstName(FIRST_NAME).lastName(LAST_NAME).address("some address").zip("some zip").city("some city").build();
    }
}
