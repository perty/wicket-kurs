package se.crisp.example.quickly.view.person;

import org.junit.Test;
import se.crisp.example.quickly.view.WicketTest;

public class PersonEditPageTest extends WicketTest {

    @Test
    public void renders() throws Exception {
        tester().startPage(new PersonEditPage());
    }
}
