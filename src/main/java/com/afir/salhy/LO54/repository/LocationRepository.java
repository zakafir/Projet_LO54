package com.afir.salhy.LO54.repository;

import com.afir.salhy.LO54.entity.Course;
import com.afir.salhy.LO54.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location,Long> {

    @Query("select l from Location l where l.city like :x")
    List<Course> findLocationByCity(@Param("x")String mc);
}
