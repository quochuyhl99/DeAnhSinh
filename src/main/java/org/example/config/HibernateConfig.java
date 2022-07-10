package org.example.config;


import java.util.Properties;
import java.util.Set;
import javax.persistence.Entity;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.reflections.Reflections;

public class HibernateConfig {
    private enum DB {
        DB_DRIVER("com.microsoft.sqlserver.jdbc.SQLServerDriver"),
        SERVER_ADDRESS("localhost"),
        DB_NAME("hibernate_de_anh_sinh"),
        SERVER_PORT("1434"),
        DIALECT("org.hibernate.dialect.SQLServer2012Dialect"),
        BASE_ENTITES_PACKAGE("org.example.entity");

        private final String value;

        DB(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    private HibernateConfig() {
    }

    public static final String URL = "jdbc:sqlserver://" + DB.SERVER_ADDRESS.getValue() + ":"
            + DB.SERVER_PORT.getValue() + ";database=" + DB.DB_NAME.getValue()
            + ";encrypt=true;trustServerCertificate=true;integratedSecurity=true;";

    private static final SessionFactory sessionFactory = createSessionFactory();
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static final Validator validator = createValidator();
    public static Validator getValidator() {
        return validator;
    }
    private static SessionFactory createSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            Configuration hibernateConfiguration = new Configuration();

            Properties hibernateProperties = new Properties();
            hibernateProperties.put(Environment.DRIVER, DB.DB_DRIVER.getValue());
            hibernateProperties.put(Environment.URL, URL);
            hibernateProperties.put(Environment.DIALECT, DB.DIALECT.getValue());
            hibernateProperties.put(Environment.SHOW_SQL, "false");
            hibernateProperties.put(Environment.FORMAT_SQL, "true");
            hibernateProperties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            hibernateProperties.put(Environment.HBM2DDL_AUTO, "create");
            hibernateProperties.put(Environment.C3P0_MIN_SIZE, "5");
            hibernateProperties.put(Environment.C3P0_MAX_SIZE, "20");
            hibernateProperties.put(Environment.C3P0_TIMEOUT, "300");
            hibernateProperties.put(Environment.C3P0_MAX_STATEMENTS, "50");
            hibernateProperties.put(Environment.C3P0_IDLE_TEST_PERIOD, "3000");

            hibernateConfiguration.setProperties(hibernateProperties);

            Set<Class<?>> classes = new Reflections(DB.BASE_ENTITES_PACKAGE.getValue())
                .getTypesAnnotatedWith(Entity.class);

            if (classes.isEmpty()) {
                System.out.println("Fail to scan Entities.");
                return null;
            }

            for (Class<?> class1 : classes) {
                hibernateConfiguration.addAnnotatedClass(class1);
            }

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(hibernateConfiguration.getProperties()).build();

            sessionFactory = hibernateConfiguration.buildSessionFactory(
                serviceRegistry);

            if (sessionFactory != null) {
                System.out.println("Create Session factory successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    private static Validator createValidator() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        return validatorFactory.getValidator();
    }
}
