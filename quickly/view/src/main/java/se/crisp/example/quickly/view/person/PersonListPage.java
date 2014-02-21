package se.crisp.example.quickly.view.person;


import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import se.crisp.example.quickly.core.Person;
import se.crisp.example.quickly.core.PersonService;
import se.crisp.example.quickly.view.BasePage;

import javax.inject.Inject;
import java.util.List;

public class PersonListPage extends BasePage<List<Person>> {

    @Inject
    private PersonService personService;

    public PersonListPage() {
        add(new ListView<Person>("row", modelForList()) {
            @Override
            protected void populateItem(ListItem<Person> item) {
                item.setModel(new CompoundPropertyModel<>(item.getModel()));
                item.add(new Label("firstName"));
                item.add(new Label("lastName"));
            }
        });
    }

    private IModel<List<Person>> modelForList() {
        return new LoadableDetachableModel<List<Person>>() {
            @Override
            protected List<Person> load() {
                return personService.findAll();
            }
        };
    }
}
