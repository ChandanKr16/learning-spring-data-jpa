package me.chandankumar.learningspringdatajpa.repositories;

import me.chandankumar.learningspringdatajpa.entities.StudentIdCard;
import org.springframework.data.repository.CrudRepository;

public interface StudentIdCardRepository extends CrudRepository<StudentIdCard, Long> {
}
