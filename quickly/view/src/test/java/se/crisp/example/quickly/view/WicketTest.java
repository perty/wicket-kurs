package se.crisp.example.quickly.view;

import org.apache.wicket.Component;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.spring.test.ApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Field;

@RunWith(MockitoJUnitRunner.class)
public abstract class WicketTest {

    private WicketTester tester;

    @Before
    public final void init() throws Exception {
        tester = new WicketTester(new WicketApplication() {
            @Override
            protected SpringComponentInjector getSpringInjector() {
                return new SpringComponentInjector(this, context(), true);
            }
        });
    }

    protected WicketTester tester() {
        return tester;
    }

    private ApplicationContext context() {
        ApplicationContextMock ctx = new MockingApplicationContext();
        populateContext(ctx);
        return ctx;
    }

    private void populateContext(ApplicationContextMock ctx) {
        for (Class<?> clazz = getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                boolean wasAccessible = field.isAccessible();
                field.setAccessible(true);
                if (field.getAnnotation(Mock.class) != null) {
                    try {
                        ctx.putBean(field.get(this));
                    } catch (IllegalAccessException e) {
                        System.out.println("Failed to add mock to context: " + field.getType().getSimpleName());
                    }
                }
                field.setAccessible(wasAccessible);
            }
        }
    }

    protected static String path(Object... parts) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            if (parts[i] instanceof Component) {
                sb.append(((Component) parts[i]).getId());
            } else {
                sb.append(parts[i]);
            }
            if (i + 1 < parts.length)
                sb.append(Component.PATH_SEPARATOR);
        }
        return sb.toString();
    }
}
