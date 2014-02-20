package se.crisp.example.quickly.view;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;
import org.springframework.data.domain.Sort;
import se.crisp.example.quickly.core.Person;
import se.crisp.example.quickly.core.PersonService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TablePage extends BasePage<Person> {
    public static final String ROW = "row";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String ADDRESS = "address";
    public static final String ZIP = "zip";
    public static final String CITY = "city";
    public static final String ROW_ADD = "rowAdd";
    public static final String FORM = "form";
    private static final String REMOVE = "remove";
    private static final String SORT = "sort";
    private static final List<String> headers = Arrays.asList(
            FIRST_NAME,
            LAST_NAME,
            ADDRESS,
            ZIP,
            CITY
    );
    @Inject
    PersonService personService;
    private PageParameters parameters;

    public TablePage(PageParameters parameters) {
        this.parameters = parameters;
        add(new TableForm(FORM, new LoadableDetachableModel<List<Person>>() {

            @Override
            protected List<Person> load() {
                return getData();
            }
        }));
    }

    private List<Person> getData() {
        StringValue stringValue = parameters.get(SORT);
        if (stringValue == null)
            return personService.findAll();
        String sortParam = stringValue.toString();
        if (headers.contains(sortParam)) {
            Sort s = new Sort(Sort.Direction.ASC, sortParam);
            return personService.findAll(s);
        }
        return personService.findAll();
    }

    private class TableForm extends Form<List<Person>> {
        public TableForm(String id, IModel<List<Person>> model) {
            super(id, model);
            RefreshingView<Person> personListView = new RefreshingView<Person>(ROW) {
                @Override
                protected void populateItem(Item<Person> item) {
                    item.setModel(new CompoundPropertyModel<>(item.getModel()));
                    item.add(createCell(FIRST_NAME, item.getModel()));
                    item.add(createCell(LAST_NAME, item.getModel()));
                    item.add(createCell(ADDRESS, item.getModel()));
                    item.add(createCell(ZIP, item.getModel()));
                    item.add(createCell(CITY, item.getModel()));
                    item.add(new AjaxButton(REMOVE) {
                        @Override
                        protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                            Person p = (Person) getParent().getDefaultModelObject();
                            personService.delete(p);
                            target.add(form);
                        }
                    });
                }

                private Component createCell(String id, final IModel<Person> personIModel) {
                    Component c = new TextField<String>(id);
                    c.add(new OnChangeAjaxBehavior() {

                        @Override
                        protected void onUpdate(AjaxRequestTarget target) {
                            Person p = personIModel.getObject();
                            personService.save(p);
                        }
                    });
                    return c;
                }

                @Override
                protected Iterator<IModel<Person>> getItemModels() {
                    ArrayList<IModel<Person>> result = new ArrayList<>();
                    for (Person p : getModelObject()) {
                        result.add(new Model<>(p));
                    }
                    return result.iterator();
                }
            };
            add(personListView);
            AjaxButton addRowButton = new AjaxButton(ROW_ADD) {
                @Override
                public void onSubmit(AjaxRequestTarget target, Form form) {
                    personService.save(new Person());
                    target.add(form);
                }
            };
            add(addRowButton);
            for(String s : headers) {
                addSortHeader(s);
            }
        }

        private void addSortHeader(final String s) {
            add(new Link<Void>("sort" + s) {

                @Override
                public void onClick() {
                    setResponsePage(new TablePage(new PageParameters().add(SORT, s)));
                }
            });
        }


    }
}
