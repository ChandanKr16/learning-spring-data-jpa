package me.chandankumar.learningspringdatajpa.repositories;

import me.chandankumar.learningspringdatajpa.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


    Optional<Student> findStudentByEmail(String email);

    List<Student> findStudentsByFirstNameStartsWith(String firstName);

    @Query("SELECT s FROM Student s WHERE s.age >= :age")
    List<Student> findStudentsByAgeGreaterThan(Integer age);

    @Query(value = "SELECT * FROM Student WHERE age >= :age AND first_name LIKE :firstName",
            nativeQuery = true)
    List<Student> findStudentsByAgeAndFirstName(Integer age, String firstName);

}
