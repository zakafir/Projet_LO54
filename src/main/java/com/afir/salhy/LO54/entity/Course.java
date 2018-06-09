package com.afir.salhy.LO54.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "COURSE")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CODE_COURSE")
    private Long code;
    @Column(unique = true)
    private String title;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "course")
    private Collection<CourseSession> sessions;

    public Collection<CourseSession> getSessions() {
        return sessions;
    }

    public void setSessions(Collection<CourseSession> sessions) {
        this.sessions = sessions;
    }

    public Course() {

    }

    public Course(String title) {
        this.title = title;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Course{" +
                "code=" + code +
                ", title='" + title + '\'' +
                '}';
    }
}
