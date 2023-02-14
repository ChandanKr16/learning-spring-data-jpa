package me.chandankumar.learningspringdatajpa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Student")
@Table(name = "student",
        uniqueConstraints = {
        @UniqueConstraint(name = "student_email_unique", columnNames = "email")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @SequenceGenerator(name = "student_seq", sequenceName = "student_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String firstName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Integer age;

    @OneToOne(mappedBy = "student",
        orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private StudentIdCard studentIdCard;

    @OneToMany(mappedBy = "student",
    orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.EAGER
    )
    private List<Book> books = new ArrayList<>();

//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
//            fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "enrollment",
//            joinColumns = @JoinColumn(
//                    name = "student_id",
//                    foreignKey = @ForeignKey(name = "enrollment_student_id_fk")
//            ),
//            inverseJoinColumns = @JoinColumn(
//                    name = "course_id",
//                    foreignKey = @ForeignKey(name = "enrollment_course_id_fk")
//            )
//    )

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
    mappedBy = "student")
    private List<Enrollment> enrollments = new ArrayList<>();

    public Student(String firstName, String lastName, String email, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public void addBook(Book book){
        if(!this.books.contains(book)){
            books.add(book);
            book.setStudent(this);
        }
    }


    public void removeBook(Book book){
        if(this.books.contains(book)){
            this.books.remove(book);
            book.setStudent(null);
        }
    }

//    public List<Course> getCourses(){
//        return courses;
//    }
//
//    public void enrollToCourse(Course course){
//        courses.add(course);
//        course.getStudents().add(this);
//    }
//
//    public void unEnrollCourse(Course course){
//        courses.remove(course);
//        course.getStudents().remove(this);
//    }



    public void addEnrollment(Enrollment enrollment){
        if(!enrollments.contains(enrollment)){
            enrollments.add(enrollment);
        }
    }

    public void removeEnrollment(Enrollment enrollment){
        enrollments.remove(enrollment);
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", studentIdCard=" + studentIdCard +
                ", books=" + books +
                '}';
    }
}
