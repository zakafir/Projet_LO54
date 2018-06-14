package com.afir.salhy.LO54.repository;

import com.afir.salhy.LO54.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select c from Student c where c.email like :x")
    List<Student> findStudentByEmail(@Param("x") String mc);

    @Query("select c from Student c where c.firstName like :x")
    Page<Student> findStudentByFirstName(@Param("x") String mc, Pageable pageable);

    @Query("select c from Student c where c.lastName like :x")
    List<Student> findStudentByLastName(@Param("x") String mc);

    @Query("select c from Student c where c.phone like :x")
    List<Student> findStudentByPhone(@Param("x") String mc);
}
