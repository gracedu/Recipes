package trainingProject.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import trainingProject.util.HibernateUtil;

@Configuration
public class HibernateConfig {
    @Bean
    public SessionFactory getHibernateSessionFactory() {
        HibernateUtil hibernateUtil = new HibernateUtil();
        return hibernateUtil.getSessionFactory();
    }
}
