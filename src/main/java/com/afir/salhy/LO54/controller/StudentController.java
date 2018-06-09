package com.afir.salhy.LO54.controller;

import com.afir.salhy.LO54.entity.CourseSession;
import com.afir.salhy.LO54.repository.StudentRepository;
import com.afir.salhy.LO54.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    private Long idSession;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(@Autowired Model model, @Valid Student student, BindingResult bindingResult, Long id){
        id = idSession;
        student.courseSession = new CourseSession();

        //student.courseSession.setId(idSession);
        model.addAttribute("student", student);
        model.addAttribute("idSession", id);
        if(bindingResult.hasErrors()){
            return "formStudent";
        }
        studentRepository.save(student);
        return "confirmation";
    }

    @RequestMapping(value = "/form",method = RequestMethod.GET)
    public String formStudent(Model model, Long id){
        model.addAttribute("student", new Student(id));
        model.addAttribute("idSessions",id);
        idSession = id;
        return "formStudent";
    }
}
