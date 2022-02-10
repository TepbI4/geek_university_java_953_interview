package ru.gb.alekseiterentev.hibernate.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.alekseiterentev.hibernate.model.Student;
import ru.gb.alekseiterentev.hibernate.repositories.StudentRepository;
import ru.gb.alekseiterentev.hibernate.services.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudent(Long id) {
        return studentRepository.findStudent(id);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void delete(Student student) {
        studentRepository.delete(student);
    }
}
