package ru.gb.alekseiterentev.hibernate.repositories.impl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gb.alekseiterentev.hibernate.db.SessionFactoryBean;
import ru.gb.alekseiterentev.hibernate.model.Student;
import ru.gb.alekseiterentev.hibernate.repositories.StudentRepository;

import java.util.List;

@Component
public class StudentRepositoryImpl implements StudentRepository {

    private SessionFactoryBean sessionFactoryBean;

    @Autowired
    public void setSessionFactoryBean(SessionFactoryBean sessionFactoryBean) {
        this.sessionFactoryBean = sessionFactoryBean;
    }

    @Override
    public List<Student> findAll() {
        try(Session session = sessionFactoryBean.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            List<Student> students = session.createQuery("from Student").getResultList();
            session.getTransaction().commit();
            return students;
        }
    }

    @Override
    public Student findStudent(Long id) {
        try(Session session = sessionFactoryBean.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.getTransaction().commit();
            return student;
        }
    }

    @Override
    public void save(Student student) {
        try(Session session = sessionFactoryBean.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(student);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Student student) {
        try(Session session = sessionFactoryBean.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.delete(student);
            session.getTransaction().commit();
        }
    }
}
