package se.crisp.example.quickly.view;

import org.apache.wicket.Application;
import org.apache.wicket.markup.html.GenericWebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class BasePage<T> extends GenericWebPage<T> {

    protected BasePage() {
        super();
        add(new Link<HomePage>("homeLink") {

            @Override
            public void onClick() {
                setResponsePage(Application.get().getHomePage());
            }
        });
    }

    protected BasePage(IModel<T> model) {
        super(model);
    }

    protected BasePage(PageParameters parameters) {
        super(parameters);
    }
}
