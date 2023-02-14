package me.chandankumar.learningspringdatajpa.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Course")
@Table(name = "course")
@Data
@NoArgsConstructor
public class Course {

    @Id
    @SequenceGenerator(name = "student_course_id_seq",
            sequenceName = "student_course_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_course_id_seq")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String department;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

    public Course(String name, String department) {
        this.name = name;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
