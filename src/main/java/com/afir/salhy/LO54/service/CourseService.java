package com.afir.salhy.LO54.service;

import com.afir.salhy.LO54.entity.Course;
import com.afir.salhy.LO54.entity.Location;
import com.afir.salhy.LO54.repository.CourseRepository;
import com.afir.salhy.LO54.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LocationRepository locationRepository;

    public Iterable<Course> getAllCourse(){
        return courseRepository.findAll();
    }

    public Iterable<Course> getCourseByCode(String courseCode){
        return courseRepository.findByCodeEquals(courseCode);
    }

    public Iterable<Course> getCourseByTitle(String courseTitle){
        return courseRepository.findByTitleContainingIgnoreCase(courseTitle);
    }

    public Iterable<Course> getCourseBySessionLocation(Location location){
        return courseRepository.findBySessionLocation(location);
    }

    public Iterable<Location> getAllLocation() {
        return locationRepository.findAll();
    }
}
