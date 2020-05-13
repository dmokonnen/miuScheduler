package edu.miu.cs425.mumsched.controller;

import edu.miu.cs425.mumsched.domain.Block;
import edu.miu.cs425.mumsched.domain.Course;
import edu.miu.cs425.mumsched.domain.Entry;
import edu.miu.cs425.mumsched.services.BlockService;
import edu.miu.cs425.mumsched.services.CourseService;
import edu.miu.cs425.mumsched.services.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Demisew Mokonnen, Dereje Enkossa, Tsegaye Beza, Bekalu Assegid
 * @2020
 */
@Controller
@RequestMapping("/admin")
public class CourseController {
    @Autowired
    CourseService courseService;
//    @Autowired
//    EntryService entryService;
    @GetMapping("/course")
    public String courseForm(@ModelAttribute("newCourse") Course course, Model model){
        List<String> courseNumberList = new ArrayList<>();
        List<Course>  courseList = new ArrayList<>(courseService.getAllCourses());
        for (Course course1: courseList)
            courseNumberList.add(course1.getCourseNumber());
        model.addAttribute("courseNumberList",courseNumberList);
        model.addAttribute("newCourse",course);
        return "/admin/addCourses";
    }
    @PostMapping("/addNewCourse")
    public String addNewCourse(@ModelAttribute("newCourse") Course course, Model model){
//        Entry entry=entryService.getEntryByEntryName(block.getEntryName());
        Course course1 = courseService.findCourseByCourseNumber(course.getPrerequisit());
        if(course1!=null)
            course.setPrerequisites(course1);
//        entry.addBlock(block);
        courseService.save(course);
        return "redirect:/admin/course";
    }
    @GetMapping("/courses")
    public String courseList(@ModelAttribute Course course, Model model){
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses",courses);
        return "admin/courselist";
    }

    @GetMapping("/editcourse/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {

        Course course = courseService.findCourseById(id);
        //.orElseThrow(()-> new IllegalArgumentException("Invalid Course Id:" + id));
        model.addAttribute("course",course);
        return "admin/update-course";
    }

    @PostMapping("/updatecourse/{id}")
    public String updateentry(@PathVariable("id") long id, @Valid Course course, BindingResult result,
                              Model model) {
        if (result.hasErrors()) {
            course.setId(id);
            return "update-course";
        }
        course.setId(id);
        courseService.save(course);
        model.addAttribute("block", courseService.getAllCourses());
        return "redirect:/admin/courses";
    }
    @GetMapping("deletecourse/{id}")
    public String deleteCourse(@PathVariable("id") long id, Model model) {
        Course course = courseService.findCourseById(id);
        //.orElseThrow(()-> new IllegalArgumentException("Invalid student Id:" + id));

        courseService.delete(course);
        model.addAttribute("course", courseService.getAllCourses());
        return "redirect:/admin/courses";
    }

}
