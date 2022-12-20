package com.example.StudentDB.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.StudentDB.model.Student;

public interface StudentRepository extends JpaRepository<Student,Long>{

}
