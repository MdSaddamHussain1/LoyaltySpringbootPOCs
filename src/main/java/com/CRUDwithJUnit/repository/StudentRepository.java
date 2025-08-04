package com.CRUDwithJUnit.repository;


import com.CRUDwithJUnit.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}