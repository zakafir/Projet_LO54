package com.afir.salhy.LO54.controller;

import com.afir.salhy.LO54.entity.Location;
import com.afir.salhy.LO54.repository.CourseSessionRepository;
import com.afir.salhy.LO54.entity.CourseSession;
import com.afir.salhy.LO54.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@Controller
public class CourseSessionController {

    @Autowired
    private CourseSessionRepository courseSessionRepository;

    @Autowired
    private LocationRepository locationRepository;

    @RequestMapping(value = "/index")
    public String courseSessions(Model model,
                              @RequestParam(name = "page", defaultValue="0")int p,
                              @RequestParam(name = "size", defaultValue="9")int s,
                              @RequestParam(name = "kw", defaultValue="")String kw,
                              @RequestParam(name = "sortBy", defaultValue="")String sortBy,
                              @RequestParam(name = "using", defaultValue="")String using){

        try {
            model.addAttribute("size",s);
            model.addAttribute("currentPage",p);
            model.addAttribute("keyWord",kw);
            model.addAttribute("sortBy",sortBy);
            model.addAttribute("using",using);

            model.addAttribute("endDate");
            model.addAttribute("startDate");
            model.addAttribute("maxNumberOfStudents");
            model.addAttribute("id");

            Page <CourseSession> courseSessions;
            if(sortBy.equals("maxNumberOfStudents")){
                courseSessions = courseSessionRepository.
                        findByTitle("%"+kw+"%",new PageRequest(p,s,Sort.Direction.ASC,"maxNumberOfStudents"));
            }else if(sortBy.equals("endDate")){
                courseSessions = courseSessionRepository.
                        findByTitle("%"+kw+"%",new PageRequest(p,s,Sort.Direction.ASC,"endDate"));
            }else if(sortBy.equals("startDate")){
                courseSessions = courseSessionRepository.
                        findByTitle("%"+kw+"%",new PageRequest(p,s,Sort.Direction.ASC,"startDate"));
            }else if(sortBy.equals("id")){
                courseSessions = courseSessionRepository.
                        findByTitle("%"+kw+"%",new PageRequest(p,s,Sort.Direction.ASC,"id"));
            }else{
                courseSessions = courseSessionRepository.
                        findByTitle("%"+kw+"%",new PageRequest(p,s));
            }

            Iterable<Location> locationList = this.getAllLocation();
            model.addAttribute("locationList", locationList);
            model.addAttribute("listSessions",courseSessions.getContent());

            int[]pages = new int[courseSessions.getTotalPages()];
            model.addAttribute("pages",pages);

        }catch (Exception e){
                model.addAttribute("exception",e);
        }
        return "courseSessions";
    }

    /**
     * Handle request for courses filtered by location
     */
    @RequestMapping(params = "locationId", method = GET)
    public String courseBySessionLocation(@RequestParam("locationId") Long locationId, Model model,
                                          @RequestParam(name = "page", defaultValue="0")int p,
                                          @RequestParam(name = "size", defaultValue="9")int s){

        model.addAttribute("size",s);
        model.addAttribute("currentPage",p);
        Location location = new Location();
        location.setId(locationId);

        Iterable<CourseSession> courseList = this.getCourseBySessionLocation(locationId,p,s);
        return "courseSessions";
        //(model, courseList);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Long id, String keyWord, int page, int size){
        return "redirect:/user/form?page="+page+"&size="+size+"&kw="+keyWord+"&id="+id;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model, Long id){
        CourseSession cs = courseSessionRepository.getOne(id);
        model.addAttribute("courseSession",cs);
        return "EditSessions";
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(Long id, String keyWord, int page, int size){
        courseSessionRepository.deleteById(id);
        return "redirect:/index?page="+page+"&size="+size+"&keyWord="+keyWord;
    }


    @RequestMapping(value = "/saveSession",method = RequestMethod.POST)
    public String saveSession(Model model, @Valid CourseSession cs, BindingResult bindingResult){
        model.addAttribute("courseSession", cs);
        if(bindingResult.hasErrors()){
            return "EditSessions";
        }
        courseSessionRepository.save(cs);
        return "ConfirmationSession";
    }

    public Iterable<Location> getAllLocation() {
        return locationRepository.findAll();
    }

    public Iterable<CourseSession> getCourseBySessionLocation(Long locationId, int p, int s){
        return courseSessionRepository.findByLocationId(locationId, new PageRequest(p,s));
    }
}

