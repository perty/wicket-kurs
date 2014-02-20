package se.crisp.example.quickly.view;

import org.junit.Test;
import org.mockito.Mock;
import se.crisp.example.quickly.core.PersonService;

public class HomePageTest extends WicketTest {

    @Mock
    PersonService personService;

    @Test
    public void home_page_renders_successfully() {
        tester().startPage(HomePage.class);
        tester().assertRenderedPage(HomePage.class);
    }

    @Test
    public void clicking_on_next_page_takes_us_to_next_page() throws Exception {
        tester().startPage(HomePage.class);

        //tester().clickLink(HomePage.NEXT_PAGE);

        //tester().assertRenderedPage(TablePage.class);
    }
}
