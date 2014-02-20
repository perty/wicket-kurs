package se.crisp.example.quickly.view;

import org.junit.Test;
import org.mockito.Mock;
import se.crisp.example.quickly.core.PersonService;

public class TestHomePage extends WicketTest {

    @Mock
    PersonService personService;

    @Test
    public void homepageRendersSuccessfully() {
        tester().startPage(HomePage.class);
        tester().assertRenderedPage(HomePage.class);
    }

    @Test
    public void clicking_on_next_page_takes_us_to_table_page() throws Exception {
        tester().startPage(HomePage.class);

        tester().clickLink(HomePage.NEXT_PAGE);

        tester().assertRenderedPage(TablePage.class);
    }
}
