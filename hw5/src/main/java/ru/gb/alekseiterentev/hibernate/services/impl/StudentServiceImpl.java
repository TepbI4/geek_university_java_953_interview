package ru.gb.alekseiterentev.hibernate.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.alekseiterentev.hibernate.repositories.StudentRepository;
import ru.gb.alekseiterentev.hibernate.services.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
