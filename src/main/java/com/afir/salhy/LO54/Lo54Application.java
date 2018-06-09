package com.afir.salhy.LO54;

import com.afir.salhy.LO54.entity.*;
import com.afir.salhy.LO54.repository.CourseSessionRepository;
import com.afir.salhy.LO54.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Date;


@SpringBootApplication
public class Lo54Application implements CommandLineRunner {

    @Autowired
    private CourseSessionRepository courseSessionDao;
    @Autowired
    private StudentRepository studentDao;

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(Lo54Application.class, args);

        CourseSessionRepository courseSessionRepository = ctx.getBean(CourseSessionRepository.class);

        //courseSessionRepository.save(...);


    }

    Location l1 = new Location("Belfort");

    @Override
    public void run(String... args) throws Exception {

        Student student = studentDao.save(
                new Student("titi",
                        "tata",
                        "23rue",
                        "099887777",
                        "rr@ff.fv",
                        new CourseSession(
                                new Course("JAVA POO Part-3")
                                , new Date(15462972L)
                                , new Date(15463836L)
                                , 658
                                , new Location("Chelsea")))
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
                                , new Date(15566616L)
                                , new Date(15497532L)
                                , 153
                                ,new Location("Arsenal"))));



        Student student3 = studentDao.save(
                new Student("test",
                        "test",
                        "18 rue ernest",
                        "0559797790",
                        "test@test.fr",
                        new CourseSession(
                                new Course("JAVA POO Part-1")
                                , new Date(1549148400000L)
                                , new Date(1549753200000L)
                                , 72
                                , new Location("west ham united"))));

    }
}
