package ru.gb.alekseiterentev.hibernate.services;

import ru.gb.alekseiterentev.hibernate.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student findStudent(Long id);
    void save(Student student);
    void delete(Student student);
}
