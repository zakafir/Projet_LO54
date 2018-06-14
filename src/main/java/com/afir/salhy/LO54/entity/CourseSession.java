package com.afir.salhy.LO54.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "COURSE_SESSION")
public class CourseSession implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_COURSE_SESSION")
    private Long id;
    @NotNull
    @DateTimeFormat(iso=ISO.DATE)
    private LocalDate startDate;

    @NotNull
    @DateTimeFormat(iso=ISO.DATE)
    private LocalDate endDate;
    private int maxNumberOfStudents;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="CODE_COURSE")
    private Course course;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ID_LOCATION")
    private Location location;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseSession")
    private Collection<Student> students;


    public CourseSession() {

    }

    public CourseSession(Long id){
        this.id = id;
    }

    public CourseSession(Course course, LocalDate startDate, LocalDate endDate, int maxNumberOfStudents, Location location) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxNumberOfStudents = maxNumberOfStudents;
        this.course = course;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "CourseSession{" +
                "id=" + id +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Collection<Student> getStudent() {
        return students;
    }

    public void setStudent(Collection<Student> students) {
        this.students = students;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getMaxNumberOfStudents() {
        return maxNumberOfStudents;
    }

    public void setMaxNumberOfStudents(int maxNumberOfStudents) {
        this.maxNumberOfStudents = maxNumberOfStudents;
    }
}
