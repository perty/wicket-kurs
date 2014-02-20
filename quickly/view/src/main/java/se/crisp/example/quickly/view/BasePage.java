package se.crisp.example.quickly.view;

import org.apache.wicket.Application;
import org.apache.wicket.markup.html.GenericWebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class BasePage<T> extends GenericWebPage<T> {

    protected BasePage() {
        init();
    }

    protected BasePage(IModel<T> model) {
        super(model);
        init();
    }

    protected BasePage(PageParameters parameters) {
        super(parameters);
        init();
    }

    private void init() {
        add(new Link<HomePage>("homeLink") {

            @Override
            public void onClick() {
                setResponsePage(Application.get().getHomePage());
            }
        });
    }
}
