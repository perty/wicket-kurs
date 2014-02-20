package se.crisp.example.quickly.view;

import org.apache.wicket.markup.html.list.ListView;
import org.junit.Test;
import org.mockito.Mock;
import se.crisp.example.quickly.core.Person;
import se.crisp.example.quickly.core.PersonService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static se.crisp.example.quickly.view.TablePage.*;

public class TablePageTest extends WicketTest {

    @Mock
    PersonService personService;

    @Test
    public void page_loads_data_from_database() throws Exception {
        tester().startPage(TablePage.class);

        verify(personService).findAll();
    }

    @Test
    public void changing_a_row_saves_the_data() throws Exception {
        List<Person> persons = Arrays.asList(PersonCreator.createSomePerson());
        when(personService.findAll()).thenReturn(persons);

        tester().startPage(TablePage.class);
//        tester().executeAjaxEvent(path(FORM, ROW, 0, FIRST_NAME), "onclick");

    }

    @Test
    public void when_clicking_on_add_button_then_add_row() throws Exception {

        tester().startPage(TablePage.class);
        tester().executeAjaxEvent(path(FORM, ROW_ADD), "onclick");

        verify(personService).save(any(Person.class));
    }

    private int getViewSize() {
        ListView component = (ListView) tester().getComponentFromLastRenderedPage(path(FORM, ROW));
        return component.getViewSize();
    }
}
