package com.afir.salhy.LO54.controller;

import com.afir.salhy.LO54.entity.Location;
import com.afir.salhy.LO54.repository.CourseSessionRepository;
import com.afir.salhy.LO54.entity.CourseSession;
import com.afir.salhy.LO54.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

import java.time.LocalDate;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@Controller
public class CourseSessionController {

    @Autowired
    private CourseSessionRepository courseSessionRepository;

    @Autowired
    private LocationRepository locationRepository;

    @RequestMapping(value = "/locations")
    public String courseSessionLocation(Model model,
                                        @RequestParam(name = "page", defaultValue = "0") int p,
                                        @RequestParam(name = "size", defaultValue = "9") int s,
                                        @RequestParam(name = "city", defaultValue = "") String city) {

        model.addAttribute("size", s);
        model.addAttribute("currentPage", p);
        model.addAttribute("city", city);
        model.addAttribute("Paris");
        model.addAttribute("Londres");
        model.addAttribute("Madrid");


        Iterable<Location> locationList = this.getAllLocation();
        model.addAttribute("locationList", locationList);

        for (Location l : locationList) {
            model.addAttribute(l.getCity(), l);
        }

        Page<CourseSession> courseSessions;
        if (city.equals("Paris")) {
            courseSessions = courseSessionRepository.findByCity("Paris", new PageRequest(p, s));
        } else if (city.equals("Londres")) {
            courseSessions = courseSessionRepository.findByCity("Londres", new PageRequest(p, s));
        } else {
            courseSessions = courseSessionRepository.findByCity("Madrid", new PageRequest(p, s));
        }
        model.addAttribute("listSessions", courseSessions.getContent());

        model.addAttribute("listelocation", locationRepository.findAll());

        int[] pages = new int[courseSessions.getTotalPages()];
        model.addAttribute("pages", pages);
        return "courseSessions";
    }


    @RequestMapping(value = "/index")
    public String courseSessions(Model model,
                                 @RequestParam(name = "page", defaultValue = "0") int p,
                                 @RequestParam(name = "size", defaultValue = "9") int s,
                                 @RequestParam(name = "kw", defaultValue = "") String kw,
                                 @RequestParam(name = "sortBy", defaultValue = "") String sortBy,
                                 @RequestParam(name = "using", defaultValue = "") String using,
                                 @RequestParam(name = "finding", defaultValue = "") String finding,
                                 @RequestParam(name = "with", defaultValue = "") String with,
                                 @RequestParam(name = "city", defaultValue = "") Long city) {

        try {
            model.addAttribute("size", s);
            model.addAttribute("currentPage", p);
            model.addAttribute("keyWord", kw);
            model.addAttribute("sortBy", sortBy);
            model.addAttribute("using", using);
            model.addAttribute("finding", finding);
            model.addAttribute("with", with);

            model.addAttribute("endDate");
            model.addAttribute("startDate");
            model.addAttribute("maxNumberOfStudents");
            model.addAttribute("ASC");
            model.addAttribute("DESC");
            model.addAttribute("id");
            model.addAttribute("myDate");
            model.addAttribute("city", city);


            Page<CourseSession> courseSessions;
            if (using.equals("ASC")) {
                if (sortBy.equals("maxNumberOfStudents")) {
                    courseSessions = courseSessionRepository.
                            findByTitle("%" + kw + "%", new PageRequest(p, s, Sort.Direction.ASC, "maxNumberOfStudents"));
                } else if (sortBy.equals("endDate")) {
                    courseSessions = courseSessionRepository.
                            findByTitle("%" + kw + "%", new PageRequest(p, s, Sort.Direction.ASC, "endDate"));
                } else if (sortBy.equals("startDate")) {
                    courseSessions = courseSessionRepository.
                            findByTitle("%" + kw + "%", new PageRequest(p, s, Sort.Direction.ASC, "startDate"));
                } else if (sortBy.equals("id")) {
                    courseSessions = courseSessionRepository.
                            findByTitle("%" + kw + "%", new PageRequest(p, s, Sort.Direction.ASC, "id"));
                } else {
                    courseSessions = courseSessionRepository.
                            findByTitle("%" + kw + "%", new PageRequest(p, s));
                }
            } else {
                if (sortBy.equals("maxNumberOfStudents")) {
                    courseSessions = courseSessionRepository.
                            findByTitle("%" + kw + "%", new PageRequest(p, s, Sort.Direction.DESC, "maxNumberOfStudents"));
                } else if (sortBy.equals("endDate")) {
                    courseSessions = courseSessionRepository.
                            findByTitle("%" + kw + "%", new PageRequest(p, s, Sort.Direction.DESC, "endDate"));
                } else if (sortBy.equals("startDate")) {
                    courseSessions = courseSessionRepository.
                            findByTitle("%" + kw + "%", new PageRequest(p, s, Sort.Direction.DESC, "startDate"));
                } else if (sortBy.equals("id")) {
                    courseSessions = courseSessionRepository.
                            findByTitle("%" + kw + "%", new PageRequest(p, s, Sort.Direction.DESC, "id"));
                } else {
                    courseSessions = courseSessionRepository.
                            findByTitle("%" + kw + "%", new PageRequest(p, s));
                }
            }

            if (with.equals("startDate")) {
                if (finding.equals("2015")) {

                    String date = "2015-01-01";

                    //default, ISO_LOCAL_DATE
                    LocalDate localDate = LocalDate.parse(date);

                    courseSessions = courseSessionRepository.
                            findByStartDate(localDate, new PageRequest(p, s, Sort.Direction.DESC, "endDate"));

                } else if (finding.equals("2016")) {

                    String date = "2016-01-01";

                    //default, ISO_LOCAL_DATE
                    LocalDate localDate = LocalDate.parse(date);

                    courseSessions = courseSessionRepository.
                            findByStartDate(localDate, new PageRequest(p, s, Sort.Direction.DESC, "endDate"));

                } else if (finding.equals("2017")) {

                    String date = "2017-01-01";

                    //default, ISO_LOCAL_DATE
                    LocalDate localDate = LocalDate.parse(date);

                    courseSessions = courseSessionRepository.
                            findByStartDate(localDate, new PageRequest(p, s, Sort.Direction.DESC, "endDate"));

                } else if (finding.equals("2018")) {

                    String date = "2018-01-01";

                    //default, ISO_LOCAL_DATE
                    LocalDate localDate = LocalDate.parse(date);

                    courseSessions = courseSessionRepository.
                            findByStartDate(localDate, new PageRequest(p, s, Sort.Direction.DESC, "endDate"));

                }

            }
            if (with.equals("endDate")) {
                if (finding.equals("2015")) {

                    String date = "2015-01-01";

                    //default, ISO_LOCAL_DATE
                    LocalDate localDate = LocalDate.parse(date);

                    courseSessions = courseSessionRepository.
                            findByEndDate(localDate, new PageRequest(p, s, Sort.Direction.DESC, "endDate"));

                } else if (finding.equals("2016")) {

                    String date = "2016-01-01";

                    //default, ISO_LOCAL_DATE
                    LocalDate localDate = LocalDate.parse(date);

                    courseSessions = courseSessionRepository.
                            findByEndDate(localDate, new PageRequest(p, s, Sort.Direction.DESC, "endDate"));

                } else if (finding.equals("2017")) {

                    String date = "2017-01-01";

                    //default, ISO_LOCAL_DATE
                    LocalDate localDate = LocalDate.parse(date);

                    courseSessions = courseSessionRepository.
                            findByEndDate(localDate, new PageRequest(p, s, Sort.Direction.DESC, "endDate"));

                } else if (finding.equals("2018")) {

                    String date = "2018-01-01";

                    //default, ISO_LOCAL_DATE
                    LocalDate localDate = LocalDate.parse(date);

                    courseSessions = courseSessionRepository.
                            findByEndDate(localDate, new PageRequest(p, s, Sort.Direction.DESC, "endDate"));

                }
            }


            model.addAttribute("listSessions", courseSessions.getContent());

            model.addAttribute("listelocation", locationRepository.findAll());

            int[] pages = new int[courseSessions.getTotalPages()];
            model.addAttribute("pages", pages);

        } catch (Exception e) {
            model.addAttribute("exception", e);
            System.out.println(e.getMessage());
        }
        return "courseSessions";
    }

    /**
     * Handle request for courses filtered by location
     */
    @RequestMapping(params = "locationId", method = GET)
    public String courseBySessionLocation(@RequestParam("locationId") Long locationId, Model model,
                                          @RequestParam(name = "page", defaultValue = "0") int p,
                                          @RequestParam(name = "size", defaultValue = "9") int s) {

        model.addAttribute("size", s);
        model.addAttribute("currentPage", p);
        Location location = new Location();
        location.setId(locationId);

        return "courseSessions";
        //(model, courseList);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Long id, String keyWord, int page, int size) {
        return "redirect:/form?page=" + page + "&size=" + size + "&kw=" + keyWord + "&id=" + id;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model, Long id) {
        CourseSession cs = courseSessionRepository.getOne(id);
        model.addAttribute("courseSession", cs);
        return "editSessions";
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(Long id, String keyWord, int page, int size) {
        courseSessionRepository.deleteById(id);
        return "redirect:/index?page=" + page + "&size=" + size + "&keyWord=" + keyWord;
    }


    @RequestMapping(value = "/saveSession", method = RequestMethod.POST)
    public String saveSession(Model model, @Valid CourseSession cs, BindingResult bindingResult, Long id) {
        if (courseSessionRepository.existsById(id)) {
            model.addAttribute("courseSession", cs);
            if (bindingResult.hasErrors()) {
                return "editSessions";
            }
            cs = courseSessionRepository.getOne(id);
            courseSessionRepository.saveAndFlush(cs);
        }
        return "confirmationSession";
    }

    public Iterable<Location> getAllLocation() {
        return locationRepository.findAll();
    }
}

