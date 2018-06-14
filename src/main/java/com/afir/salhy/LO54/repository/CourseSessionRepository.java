package com.afir.salhy.LO54.repository;

import com.afir.salhy.LO54.entity.CourseSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface CourseSessionRepository extends JpaRepository<CourseSession, Long> {

    @Query("select cs from CourseSession cs where cs.course.title like :x")
    Page<CourseSession> findByTitle(@Param("x") String kw, Pageable pageable);

    @Query("select cs from CourseSession cs where cs.location.city = :x")
    Page<CourseSession> findByCity(@Param("x") String c, Pageable pageable);

    @Query("select crs from CourseSession crs where crs.startDate like :d")
    Page<CourseSession> findByStartDate(@Param("d") LocalDate date, Pageable pageable);

    @Query("select crs from CourseSession crs where crs.endDate like :d")
    Page<CourseSession> findByEndDate(@Param("d") LocalDate date, Pageable pageable);

}
