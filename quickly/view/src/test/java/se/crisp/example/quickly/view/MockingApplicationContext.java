package se.crisp.example.quickly.view;

import org.apache.wicket.spring.test.ApplicationContextMock;
import org.springframework.beans.BeansException;
import org.springframework.core.env.Environment;

import java.lang.annotation.Annotation;
import java.util.Map;

public class MockingApplicationContext extends ApplicationContextMock {
    @Override
    public String getApplicationName() {
        throw new UnsupportedOperationException("MockingApplicationContext#getApplicationName not implemented yet.");
    }

    @Override
    public Environment getEnvironment() {
        throw new UnsupportedOperationException("MockingApplicationContext#getEnvironment not implemented yet.");
    }

    @Override
    public Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) throws BeansException {
        throw new UnsupportedOperationException("MockingApplicationContext#getBeansWithAnnotation not implemented yet.");
    }

    @Override
    public <A extends Annotation> A findAnnotationOnBean(String beanName, Class<A> annotationType) {
        throw new UnsupportedOperationException("MockingApplicationContext#findAnnotationOnBean not implemented yet.");
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        throw new UnsupportedOperationException("MockingApplicationContext#getBean not implemented yet.");
    }

    @Override
    public String[] getBeanNamesForType(Class type) {
        String[] names = super.getBeanNamesForType(type);
        if (names.length > 0) {
            return names;
        }
        else {
            return new String[]{type.getName()};
        }
    }
}
