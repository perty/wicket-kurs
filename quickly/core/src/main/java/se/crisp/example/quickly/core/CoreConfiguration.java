package se.crisp.example.quickly.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

import static se.crisp.example.quickly.core.CoreConfiguration.*;

@Configuration
@ComponentScan(PACKAGES_TO_SCAN)
@EnableJpaRepositories(basePackages = PACKAGES_TO_SCAN, entityManagerFactoryRef = EMF)
public class CoreConfiguration {

    public static final String PACKAGES_TO_SCAN = "se.crisp.example.quickly.core";
    public static final String EMF = "quickly";

    private DataSource fallbackDataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).build();
    }

    private DataSource dataSource() throws NamingException {
        JndiObjectFactoryBean jndi = new JndiObjectFactoryBean();
        jndi.setJndiName("java:comp/env/jdbc/defaultDS");
        jndi.setDefaultObject(fallbackDataSource());
        jndi.setExpectedType(DataSource.class);
        jndi.afterPropertiesSet();
        return (DataSource) jndi.getObject();
    }

    @Bean(name = EMF)
    public EntityManagerFactory entityManagerFactory() throws NamingException {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();

        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", "update");

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(adapter);
        factory.setDataSource(dataSource());
        factory.setPackagesToScan(PACKAGES_TO_SCAN);
        factory.setJpaProperties(jpaProperties);
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager txManager() throws NamingException {
        return new JpaTransactionManager(entityManagerFactory());
    }

    @Bean
    public PersistenceExceptionTranslator exceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

}
