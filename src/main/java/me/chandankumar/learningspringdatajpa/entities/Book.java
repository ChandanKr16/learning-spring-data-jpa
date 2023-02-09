package me.chandankumar.learningspringdatajpa.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity(name = "Book")
@Table
public class Book {

    @Id
    @SequenceGenerator(name = "book_id_seq", sequenceName = "book_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_seq")
    private Long id;

    @Column(nullable = false)
    private String bookName;

    @Column(nullable = false,
        columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime createdAt;


    @ManyToOne
    @JoinColumn(name = "student_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "student_book_fk")
    )
    private Student student;

    public Book() {
    }
    public Book(String bookName, LocalDateTime createdAt) {
        this.bookName = bookName;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Student getStudent() {
        return student;
    }

    public String getBookName() {
        return bookName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", bookName='" + bookName + '\''+
                '}';
    }
}
