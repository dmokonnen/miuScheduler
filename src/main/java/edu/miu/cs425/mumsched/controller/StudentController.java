package edu.miu.cs425.mumsched.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Demisew Mokonnen, Dereje Enkossa, Tsegaye Beza, Bekalu Assegid
 * @2020
 */
@Controller
public class StudentController {

    @GetMapping("student/viewschedule")
    public String viewSschedule(Model model) {
        //model.addAttribute("entries", entryService.getAllEntry());
        return "student/viewSchedule";
    }

    @GetMapping("student/updatecourse")
    public String updateCourse(Model model) {
        //model.addAttribute("entries", entryService.getAllEntry());
        return "student/updatecourse";
    }

    @GetMapping("student/registertosection")
    public String registerToSection(Model model) {
        //model.addAttribute("entries", entryService.getAllEntry());
        return "student/registertosection";
    }

}
