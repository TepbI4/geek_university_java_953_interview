package ru.gb.alekseiterentev.hibernate;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.gb.alekseiterentev.hibernate.model.Student;
import ru.gb.alekseiterentev.hibernate.services.StudentService;

import java.util.Arrays;
import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        StudentService studentService = context.getBean(StudentService.class);

        System.out.println(studentService.findStudent(1L));

        System.out.println();

        System.out.println(Arrays.toString(studentService.findAll().toArray()));

        System.out.println();

        Student studentForUpdate = studentService.findStudent(6L);
        System.out.println();
        System.out.println(studentForUpdate);
        System.out.println();
        studentForUpdate.setScore(40);
        studentService.save(studentForUpdate);
        System.out.println();
        System.out.println(studentService.findStudent(6L));
    }
}
