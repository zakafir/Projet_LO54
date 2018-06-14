package com.afir.salhy.LO54.repository;

import com.afir.salhy.LO54.entity.Course;
import com.afir.salhy.LO54.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from Course c where c.title like :x")
    List<Course> findCourseByTitle(@Param("x") String mc);

    public Iterable<Course> findByCodeEquals(String code);

    public Iterable<Course> findByTitleContainingIgnoreCase(String title);

    @Query("select cs.course from CourseSession cs where cs.location = ?1")
    public Iterable<Course> findBySessionLocation(Location location);
}
