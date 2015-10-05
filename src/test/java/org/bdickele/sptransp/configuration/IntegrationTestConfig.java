package org.bdickele.sptransp.configuration;

import org.bdickele.sptransp.security.SpTranspUserDetailsService;
import org.bdickele.sptransp.service.CustomerService;
import org.bdickele.sptransp.service.EmployeeService;
import org.postgresql.jdbc2.optional.SimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableTransactionManagement
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@EnableJpaRepositories(basePackages = {"org.bdickele.sptransp.repository"})
public class IntegrationTestConfig {

    @Autowired
    Environment env;

    @Bean
    public CustomerService customerService() {
        return new CustomerService();
    }

    @Bean
    public EmployeeService employeeService() {
        return new EmployeeService();
    }

    @Bean
    public SpTranspUserDetailsService userDetailsService() {
        return new SpTranspUserDetailsService();
    }

    @Bean
    public PasswordEncoder devPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"org.bdickele.sptransp.domain"});

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(getHibernateProperties());

        return em;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties getHibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.format_sql", "true");
        prop.put("hibernate.show_sql", "true");
        //prop.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        prop.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return prop;
    }

    @Bean
    public DataSource dataSource() {
        SimpleDataSource dataSource = new SimpleDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/sptransp_dev");
        dataSource.setCurrentSchema("sptransp");
        dataSource.setUser("postgres");
        dataSource.setPassword("admin");
        return dataSource;
    }

    /*
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("testdb")
                .addScript("classpath:sql/create_sequences.sql")
                .addScript("classpath:sql/create_tables.sql")
                .addScript("classpath:sql/table_grants.sql")
                .addScript("classpath:sql/basedata.sql")
                .addScript("classpath:sql/testdata_users.sql")
                .addScript("classpath:sql/testdata_agr_rules.sql")
                .build();
    }
    */

    @Bean
    public CacheManager cacheManager() {
        return new GuavaCacheManager();
    }
}
