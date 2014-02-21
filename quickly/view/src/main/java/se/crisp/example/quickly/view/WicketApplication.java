package se.crisp.example.quickly.view;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import se.crisp.example.quickly.view.person.PersonEditPage;
import se.crisp.example.quickly.view.person.PersonListPage;
import se.crisp.example.quickly.view.toc.TermsAndConditionsPage;


public class WicketApplication extends WebApplication {

    protected SpringComponentInjector getSpringInjector() {
        return new SpringComponentInjector(this);
    }

    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }


    @Override
    public void init() {
        super.init();

        getComponentInstantiationListeners().add(getSpringInjector());

        mountPage("newPerson", PersonEditPage.class);
        mountPage("listPersons", PersonListPage.class);
        mountPage("toc", TermsAndConditionsPage.class);
    }
}
