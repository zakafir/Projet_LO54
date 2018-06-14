package com.afir.salhy.LO54.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author Zakaria AFIR
 */

@Entity
@Table(name = "STUDENT")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_STUDENT")
    private Long id;
    @NotNull
    private String lastName;
    @NotNull
    private String firstName;

    @NotNull
    @Size(min = 1, max = 80)
    private String address;
    @Size(min = 1, max = 18)
    private String phone;
    @NotNull
    @Size(min = 1, max = 30)
    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_COURSE_SESSION")
    public CourseSession courseSession;


    public Student() {

    }

    public Student(Long idCourseSession) {
        this.id = idCourseSession;
    }

    public Student(String lastName, String firstName, String address, String phone, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public Student(String lastName, String firstName, String address, String phone, String email, CourseSession courseSession) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.courseSession = courseSession;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CourseSession getIdCourseSession() {
        return courseSession;
    }

    public void setIdCourseSession(CourseSession idCourseSession) {
        this.courseSession = idCourseSession;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", idCourseSession=" + courseSession +
                '}';
    }


    public CourseSession getCourseSession() {
        return courseSession;
    }

    public void setCourseSession(CourseSession courseSession) {
        this.courseSession = courseSession;
    }
}


