package me.chandankumar.learningspringdatajpa;

import com.github.javafaker.Faker;
import me.chandankumar.learningspringdatajpa.entities.Student;
import me.chandankumar.learningspringdatajpa.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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

//        Student chandan = new Student(1L, "Chandan", "Kumar", "ckp1606@gmail.com", 23);
//        Student ravi = new Student(2L, "Ravi", "Kumar", "ravi@gmail.com", 25);
//        Student aman = new Student(3L, "Chandani", "Kumari", "chandani@gmail.com", 21);

       // studentRepository.saveAll(List.of(chandan, ravi, aman));

        return args -> {

            addStudentData();

            //jpaQuery();

            //sorting();

            PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("firstName").ascending());

            studentRepository.findAll(pageRequest)
                    .getContent()
                    .forEach(System.out::println);

        };
    }

    private void sorting() {
        Sort sort = Sort.by("firstName").ascending()
                .and(Sort.by("age").descending());

        studentRepository.findAll(sort).forEach(student -> {
            System.out.println(student.getFirstName() + " " + student.getAge());
        });


//            studentRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"))
//                    .forEach(System.out::println);
    }

    private void jpaQuery() {
        studentRepository.findStudentsByFirstNameStartsWith("cha")
                .forEach(System.out::println);


        studentRepository.findStudentsByAgeGreaterThan(23)
                .forEach(System.out::println);

        studentRepository.findStudentsByAgeAndFirstName(21, "%a%")
                .forEach(System.out::println);
    }


    private void addStudentData(){

        Faker faker = new Faker();

        for(int i = 0; i < 50; i++){
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@gmail.com", firstName, lastName);
            int age = faker.random().nextInt(15, 100);

            studentRepository.save(new Student(firstName, lastName, email, age));
        }
    }

}
