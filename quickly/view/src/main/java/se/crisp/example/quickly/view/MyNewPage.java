package se.crisp.example.quickly.view;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;

public class MyNewPage extends BasePage {

    private final String breadTrail;

    public MyNewPage(String breadTrail) {
        this.breadTrail = breadTrail + "/" + Math.random();
        add(new Label("breadCrumb", new PropertyModel<>(this, "breadTrail")));
        add(new Link("nextPage") {
            @Override
            public void onClick() {
                setResponsePage(new MyNewPage(MyNewPage.this.breadTrail));
            }
        });
    }
}
