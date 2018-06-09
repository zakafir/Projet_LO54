package com.afir.salhy.LO54.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "COURSE_SESSION")
public class CourseSession implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_COURSE_SESSION")
    private Long id;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startDate;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date endDate;
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

    public CourseSession(Course course, Date startDate, Date endDate, int maxNumberOfStudents, Location location) {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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
