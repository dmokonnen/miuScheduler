package edu.miu.cs425.mumsched.registrationSubSystem.controller;

import edu.miu.cs425.mumsched.services.BlockService;
import edu.miu.cs425.mumsched.services.CourseService;
import edu.miu.cs425.mumsched.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Demisew Mokonnen
 * @2020
 */
@Controller
@RequestMapping("/section")
public class SectionController {

    BlockService blockService;
    CourseService courseService;
    FacultyService facultyService;
    @Autowired
    public SectionController(BlockService blockService, CourseService courseService, FacultyService facultyService) {
        this.blockService = blockService;
        this.courseService = courseService;
        this.facultyService = facultyService;
    }

}
