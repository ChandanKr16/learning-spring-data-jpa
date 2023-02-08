package me.chandankumar.learningspringdatajpa;

import me.chandankumar.learningspringdatajpa.entities.Student;
import me.chandankumar.learningspringdatajpa.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class LearningSpringDataJpaApplication {


    @Autowired
    StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(LearningSpringDataJpaApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(){

        Student chandan = new Student(1L, "Chandan", "Kumar", "ckp1606@gmail.com", 23);
        Student ravi = new Student(2L, "Ravi", "Kumar", "ravi@gmail.com", 25);
        Student aman = new Student(3L, "Chandani", "Kumari", "chandani@gmail.com", 21);

        studentRepository.saveAll(List.of(chandan, ravi, aman));

        return args -> {
            studentRepository.findStudentsByFirstNameStartsWith("cha")
                    .forEach(System.out::println);


            studentRepository.findStudentsByAgeGreaterThan(23)
                    .forEach(System.out::println);

            studentRepository.findStudentsByAgeAndFirstName(21, "%a%")
                    .forEach(System.out::println);
        };
    }

}
