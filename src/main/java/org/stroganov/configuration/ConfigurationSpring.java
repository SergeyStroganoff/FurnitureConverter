package org.stroganov.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan("org.stroganov")
@PropertySource("classpath:app.properties")
@PropertySource(value = {"classpath:hibernate.properties"})
public class ConfigurationSpring {

    public static final String PACKAGES_TO_SCAN = "org.stroganov.entities";
    @Autowired
    private Environment env;

    @Bean
    @Primary
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("datasource.driver"));
        dataSource.setUrl(env.getRequiredProperty("datasource.url"));
        dataSource.setUsername(env.getRequiredProperty("datasource.username"));
        dataSource.setPassword(env.getRequiredProperty("datasource.password"));
        return dataSource;
    }

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource());   // set dataSource
        sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN);
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put(AvailableSettings.DIALECT, env.getRequiredProperty("hibernate.dialect"));
        properties.put(AvailableSettings.SHOW_SQL, env.getRequiredProperty("hibernate.show_sql"));
        properties.put(AvailableSettings.STATEMENT_BATCH_SIZE, env.getRequiredProperty("hibernate.batch.size"));
        properties.put(AvailableSettings.HBM2DDL_AUTO, env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        properties.put(AvailableSettings.POOL_SIZE, env.getRequiredProperty("hibernate.connection.pool"));
        properties.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, env.getRequiredProperty("hibernate.current_session_context_class"));
        properties.put(AvailableSettings.HBM2DDL_CHARSET_NAME, env.getRequiredProperty("hibernate.connection.CharSet"));
        properties.put(AvailableSettings.FORMAT_SQL, "hibernate.sql_format");
        return properties;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }
}
