package se.crisp.example.quickly.view;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;
    public static final String NEXT_PAGE = "nextPage";

    public HomePage(final PageParameters parameters) {
        super(parameters);

        add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

        add(new Link<TablePage>(NEXT_PAGE) {
            @Override
            public void onClick() {
                setResponsePage(new TablePage(new PageParameters()));
            }
        });
    }
}
