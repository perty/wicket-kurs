package se.crisp.example.quickly.view.toc;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import se.crisp.example.quickly.view.BasePage;

public class TermsAndConditionsPage extends BasePage {

    private Boolean hasRead = Boolean.FALSE;

    public TermsAndConditionsPage() {
        final Link link = new Link("continueLink") {

            @Override
            public void onClick() {
                setResponsePage(ThankYouPage.class);
            }
        };
        link.setEnabled(false);
        link.setOutputMarkupId(true);
        add(link);

        add(new AjaxCheckBox("haveRead", Model.of(hasRead)) {

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                link.setEnabled(getModelObject());
                target.add(link);
            }
        });
    }
}
