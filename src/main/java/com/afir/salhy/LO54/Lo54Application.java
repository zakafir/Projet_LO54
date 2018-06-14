package com.afir.salhy.LO54;

import com.afir.salhy.LO54.entity.*;
import com.afir.salhy.LO54.repository.CourseSessionRepository;
import com.afir.salhy.LO54.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.Date;


@SpringBootApplication
public class Lo54Application implements CommandLineRunner {

    @Autowired
    private CourseSessionRepository courseSessionDao;
    @Autowired
    private StudentRepository studentDao;

    public static void main(String[] args) {

        SpringApplication.run(Lo54Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Student student = studentDao.save(
                new Student("titi",
                        "tata",
                        "23rue",
                        "099887777",
                        "rr@ff.fv",
                        new CourseSession(
                                new Course("JAVA POO Part-2")
                                , LocalDate.of(2015, 01, 01)
                                , LocalDate.of(2020, 01, 01)
                                , 658
                                , new Location("Londres")))
        );

        Course course = new Course("Apprenez à programmer en Python");

        Student student1 = studentDao.save(
                new Student("omar",
                        "dri",
                        "18 rue marta",
                        "09787790",
                        "omar@gmail.fr",
                        new CourseSession(
                                course
                                , LocalDate.of(2016, 01, 01)
                                , LocalDate.of(2017, 01, 01)
                                , 153
                                , new Location("Paris"))));


        Student student3 = studentDao.save(
                new Student("test",
                        "test",
                        "18 rue ernest",
                        "0559797790",
                        "test@test.fr",
                        new CourseSession(
                                new Course("JAVA POO Part-1")
                                , LocalDate.of(2017, 01, 01)
                                , LocalDate.of(2018, 01, 01)
                                , 72
                                , new Location("Madrid"))));

    }
}
