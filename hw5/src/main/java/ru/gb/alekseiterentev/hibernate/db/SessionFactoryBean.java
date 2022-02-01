package ru.gb.alekseiterentev.hibernate.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Component
public class SessionFactoryBean {

    private static final String CONFIG = "configs/student.hibernate.cfg.xml";

    private SessionFactory sessionFactory;

    @PostConstruct
    private void init() {
        forcePrepareData();
        sessionFactory = new Configuration()
                .configure(CONFIG)
                .buildSessionFactory();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private void forcePrepareData() {
        SessionFactory factory = new Configuration()
                .configure(CONFIG)
                .buildSessionFactory();
        Session session = null;
        try {
            String sql = Files.lines(Paths.get("migration.sql")).collect(Collectors.joining(" "));
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }

    @PreDestroy
    private void close() {
        sessionFactory.close();
    }
}
