package task.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import task.helpers.MessagesHelper;
import task.helpers.UsersHelper;
import task.spring.repositories.MessageRepository;
import task.spring.repositories.UserRepository;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
public class BeanConfiguration
{
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource)
    {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("task.entity");
        factory.setDataSource(dataSource);
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory)
    {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }

    @Bean
    public UsersHelper usersHelper(UserRepository userRepository)
    {
        return new UsersHelper(userRepository);
    }

    @Bean
    public MessagesHelper messagesHelper(MessageRepository messageRepository, UsersHelper usersHelper)
    {
        return new MessagesHelper(messageRepository, usersHelper);
    }

}
