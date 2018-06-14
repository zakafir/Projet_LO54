package com.afir.salhy.LO54.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "LOCATION")
public class Location implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_LOCATION")
    private Long id;
    @Column(unique = true)
    private String city;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "location")
    private Collection<CourseSession> sessions;


    public Collection<CourseSession> getSessions() {
        return sessions;
    }

    public void setSessions(Collection<CourseSession> sessions) {
        this.sessions = sessions;
    }

    public Location() {
    }

    public Location(String city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", city='" + city + '\'' +
                '}';
    }
}
