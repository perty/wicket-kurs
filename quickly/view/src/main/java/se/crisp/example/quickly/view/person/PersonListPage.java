package se.crisp.example.quickly.view.person;


import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import se.crisp.example.quickly.core.Person;
import se.crisp.example.quickly.core.PersonService;
import se.crisp.example.quickly.view.BasePage;

import javax.inject.Inject;
import java.util.List;

public class PersonListPage extends BasePage<List<Person>> {

    @Inject
    private PersonService personService;

    public PersonListPage() {
        add(new ListView<Person>("row", getTheModelOfAllPersons()) {
            @Override
            protected void populateItem(ListItem<Person> item) {
                item.add(new Label("firstName", new PropertyModel<String>(item.getModel(), "firstName")));
            }
        });
    }

    private IModel<? extends List<? extends Person>> getTheModelOfAllPersons() {
        return new LoadableDetachableModel<List<? extends Person>>() {
            @Override
            protected List<? extends Person> load() {
                return personService.findAll();
            }
        };
    }
}
