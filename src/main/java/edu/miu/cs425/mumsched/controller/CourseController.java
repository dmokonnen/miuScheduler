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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Demisew Mokonnen
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
    public String blockRegForm(@ModelAttribute("newCourse") Course course, Model model){
        List<String> courseNumberList = new ArrayList<>();
        List<Course>  courseList = new ArrayList<>(courseService.getAllCourses());
        for (Course course1: courseList)
            courseNumberList.add(course1.getCourseNumber());
        model.addAttribute("courseNumberList",courseNumberList);
        model.addAttribute("newCourse",course);
        return "/admin/addCourses";
    }
    @PostMapping("/addNewCourse")
    public String registerNewBlock(@ModelAttribute("newCourse") Course course, Model model){
//        Entry entry=entryService.getEntryByEntryName(block.getEntryName());
        Course course1 = courseService.findCourseByCourseNumber(course.getPrerequisit());
        if(course1!=null)
            course.setPrerequisites(course1);
//        entry.addBlock(block);
        courseService.save(course);
        return "redirect:/admin/course";
    }
    @GetMapping("/courses")
    public String success(@ModelAttribute Course course, Model model){
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses",courses);
        return "admin/courselist";
    }
}
