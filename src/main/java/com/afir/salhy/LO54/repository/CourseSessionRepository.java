package com.afir.salhy.LO54.repository;

import com.afir.salhy.LO54.entity.CourseSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface CourseSessionRepository extends JpaRepository<CourseSession,Long> {

    @Query("select cs from CourseSession cs where cs.course.title like :x")
    Page<CourseSession> findByTitle(@Param("x") String kw, Pageable pageable);

    @Query("select cs from CourseSession cs where cs.location.id = :x")
    Page<CourseSession> findByLocationId(@Param("x") Long locationId, Pageable pageable);

    @Query("update CourseSession cs SET cs.endDate = :endDate, cs.startDate = :startDate, cs.maxNumberOfStudents = :maxNumberOfStudents, " +
            "cs.location.city = :city, cs.course.title = :title where cs.id = :x")
    CourseSession update(@Param("endDate")Date endDate, @Param("startDate")Date startDate,
                         @Param("maxNumberOfStudents")Integer maxNumberOfStudents, @Param("city")String city, @Param("title")String title, @Param("x")Long mc);

}
