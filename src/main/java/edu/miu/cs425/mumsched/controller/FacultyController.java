package edu.miu.cs425.mumsched.controller;

import edu.miu.cs425.mumsched.domain.Course;
import edu.miu.cs425.mumsched.domain.Faculty;
import edu.miu.cs425.mumsched.services.CourseService;
import edu.miu.cs425.mumsched.services.EntryService;
import edu.miu.cs425.mumsched.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Demisew Mokonnen
 * @2020
 */
@Controller
@RequestMapping("/faculty")
public class FacultyController {
    @Autowired
    FacultyService facultyService;
    @Autowired
    CourseService courseService;
    @Autowired
    EntryService entryService;

    @GetMapping("/courses")
    public String displayCourses(Model model){
        List<Course> courses = courseService.getAllCourses();
        Faculty faculty = getLoggedInFaculty();
        Set<Course> preferredCourses= faculty.getPreferredCourses();
        model.addAttribute("courses",courses);
        model.addAttribute("preferredCourses",preferredCourses);
        model.addAttribute("course",new Course());
        return "faculty/manageCourses";
    }
    @PostMapping("/addCourse")
    public String addPreferredCourse(@Valid @ModelAttribute("course") Course preferredCourse, BindingResult bindingResult) {
        Faculty faculty = getLoggedInFaculty();
        Course course = courseService.findCourseById(preferredCourse.getId());
        if (faculty != null && course != null) {
            faculty.getPreferredCourses().add(course);
            facultyService.save(faculty);
        }
        return "redirect:/faculty/courses";
    }
    @GetMapping("/courseDelete/{id}")
    public String deletePreferredCourse(@PathVariable("id") Integer id) {
        Faculty faculty = getLoggedInFaculty();
        Set<Course> courses = faculty.getPreferredCourses();

        Iterator<Course> it = courses.iterator();
        while (it.hasNext()) {
            Course course = it.next();
            if (course.getId() == id) {
                it.remove();
            }
        }
        facultyService.save(faculty);
        return "redirect:/faculty/courses";
    }
    @GetMapping("/block")
    public String displayUnwantedBlocks(Model model) {
//        List<BlockMonths> monthBlockList = MonthUtil.getMonths();
//        model.addAttribute("monthBlockList", monthBlockList);
//        model.addAttribute("faculty",new Faculty());
//
//        // to dispaly list of courses preferred by faculty in a table
//        Faculty faculty = getLoggedInFaculty();
//        Set<BlockMonths> unwantedBlocks = faculty.getUnwantedBlocks();
//        model.addAttribute("unwantedBlocks", unwantedBlocks);
        return "faculty/manageBlock";
    }

    @PostMapping("/addBlock")
    public String addUnwantedBlocks(@ModelAttribute("faculty") Faculty faculty) {
        Faculty connectedFaculty = getLoggedInFaculty();
//        if (faculty != null) {
//            connectedFaculty.getUnwantedBlocks().addAll(faculty.getUnwantedBlocks());
//            iFacultyService.save(connectedFaculty);
//        }

        return "redirect:/faculty/block";
    }
    @GetMapping("/schedule")
    public String viewSchedule(Model model) {
        model.addAttribute("entries", entryService.getAllEntry());
        return "faculty/viewSchedule";
    }
    //To be fixed not email but user name
    public Faculty getLoggedInFaculty() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        return facultyService.findByUserName(userName);
    }
}
