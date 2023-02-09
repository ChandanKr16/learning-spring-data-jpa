package me.chandankumar.learningspringdatajpa.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity(name = "StudentIdCard")
@Table(name = "student_id_card", uniqueConstraints = {
        @UniqueConstraint(name = "student_card_number_unique",
                columnNames = "cardNumber")
})
@Getter
@Setter
@NoArgsConstructor
public class StudentIdCard {

    @Id
    @SequenceGenerator(name = "student_id_card_seq",
            sequenceName = "student_id_card_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "student_id_card_seq")
    private Long id;

    @Column(nullable = false)
    private String cardNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id",
            referencedColumnName = "id",
        foreignKey = @ForeignKey(name = "student_id_card_student_id_fk")
    )
    private Student student;

    public StudentIdCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public StudentIdCard(String cardNumber, Student student) {
        this.cardNumber = cardNumber;
        this.student = student;
    }

    @Override
    public String toString() {
        return "StudentIdCard{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }
}
