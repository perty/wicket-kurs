package se.crisp.example.quickly.view.person;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.crisp.example.quickly.core.Person;
import se.crisp.example.quickly.core.PersonService;
import se.crisp.example.quickly.view.BasePage;

import javax.inject.Inject;

public class PersonEditPage extends BasePage<Person> {

    private static final Logger LOG = LoggerFactory.getLogger(PersonEditForm.class);

    private static final String FORM = "form";

    @Inject
    private PersonService personService;

    public PersonEditPage() {
        this(Model.of(new Person()));
    }

    public PersonEditPage(IModel<Person> personModel) {
        super(personModel);
        add(new PersonEditForm(FORM, personModel));
    }

    private class PersonEditForm extends Form<Person> {
        public PersonEditForm(String id, IModel<Person> personModel) {
            super(id, personModel);
            add(new FeedbackPanel("feedback"));
            add(new TextField<>("firstName", new PropertyModel<String>(personModel, "firstName")).setRequired(true));
            add(new TextField<>("lastName", new PropertyModel<String>(personModel, "lastName")));
        }

        @Override
        protected void onSubmit() {
            personService.save(getModelObject());
            LOG.info("Saved person: " + getModelObject());
            setResponsePage(new PersonEditPage());
        }
    }
}
