package info.kkrzyzak.kk.spring.config;

import info.kkrzyzak.kk.model.ChatUser;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: karol.krzyzak@gmail.com
 * Date: 03.09.12
 * Time: 14:27
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableTransactionManagement
public class AppConfig {

    //    @Value("${jdbc.driverClassName}")
    private String driverClassName="com.mysql.jdbc.Driver";

    //    @Value("${jdbc.url}")
    private String url="jdbc:mysql://127.0.0.1:3307/db_liveChat?characterEncoding=UTF-8&amp;useUnicode=true&amp;autoCommit=false";

    //    @Value("${jdbc.username}")
    private String username="root";

    //    @Value("${jdbc.password}")
    private String password="kkrzyzak10";

    //    @Value("${hibernate.dialect}")
    private String hibernateDialect="org.hibernate.dialect.MySQL5InnoDBDialect";

    //    @Value("${hibernate.show_sql}")
    private String hibernateShowSql="true";

    //    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHbm2ddlAuto="update";


    @Bean
    public EntityManagerFactory entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(getDataSource());
        entityManagerFactory.setPackagesToScan(new String[] { "info.kkrzyzak.kk.model" });
        entityManagerFactory.setPersistenceProvider(new HibernatePersistence());
        entityManagerFactory.afterPropertiesSet();
        return entityManagerFactory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory());
        transactionManager.setDataSource(getDataSource());
        transactionManager.setJpaDialect(new HibernateJpaDialect());
        return transactionManager;
    }



    @Bean()
    public DataSource getDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(driverClassName);
        driverManagerDataSource.setUrl(url);
        driverManagerDataSource.setUsername(username);
        driverManagerDataSource.setPassword(password);
        return driverManagerDataSource;
    }

//    @Bean
//    @Autowired
//    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
//        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
//        hibernateTransactionManager.setSessionFactory(sessionFactory);
//        return hibernateTransactionManager;
//    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean annotationSessionFactoryBean = new LocalSessionFactoryBean();
        annotationSessionFactoryBean.setDataSource(getDataSource());
        annotationSessionFactoryBean.setHibernateProperties(getHibernateProperties());
        annotationSessionFactoryBean.setPackagesToScan(new String[]{"info.kkrzyzak.kk.model"});
        return annotationSessionFactoryBean;
    }

    @Bean
    public Properties getHibernateProperties() {
        Properties jpaProperties = new Properties();
        jpaProperties.put(org.hibernate.cfg.Environment.DIALECT, hibernateDialect);
        jpaProperties.put(org.hibernate.cfg.Environment.SHOW_SQL, hibernateShowSql);
        jpaProperties.put(Environment.HBM2DDL_AUTO, hibernateHbm2ddlAuto);

        return jpaProperties;
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new LocalSessionFactoryBuilder(getDataSource())
                .addAnnotatedClasses(ChatUser.class)
                .buildSessionFactory();
    }

}
