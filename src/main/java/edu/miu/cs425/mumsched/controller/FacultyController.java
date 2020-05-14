package edu.miu.cs425.mumsched.controller;

import edu.miu.cs425.mumsched.domain.Block;
import edu.miu.cs425.mumsched.domain.Course;
import edu.miu.cs425.mumsched.domain.Faculty;
import edu.miu.cs425.mumsched.services.BlockService;
import edu.miu.cs425.mumsched.services.CourseService;
import edu.miu.cs425.mumsched.services.EntryService;
import edu.miu.cs425.mumsched.services.FacultyService;
import jdk.nashorn.internal.runtime.Specialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

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
    @Autowired
    BlockService blockService;

    @GetMapping("/profileUpdate")
    public String updateFacultyProfile(Model model) {
        Faculty faculty = getLoggedInFaculty();
        model.addAttribute("faculty", faculty);
        List<String> specializations = Arrays.asList("DATA SCIENCE", "WEB APPLICATIONS", "SOFTWARE DESIGN");
        model.addAttribute("specializations", specializations);
        return "faculty/profileUpdate";
    }

    @PostMapping("/profileUpdate")
    public String saveFacultyProfile(@ModelAttribute("faculty") Faculty faculty){
        facultyService.save(faculty);
        return "redirect:/faculty/profile";
    }
    @GetMapping("/profile")
    public String displayFacultyProfile(Model model) {
        Faculty faculty = getLoggedInFaculty();
        System.out.println("this this \n \n "+faculty);
        model.addAttribute("faculty", faculty);
        return "faculty/profileManage";
    }
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
        List<String> monthBlockList=new ArrayList<>(Arrays.asList("JANUARY","FEBRUARY","MARCH","APRIL","MAY","JUNE","JULY","AUGUST",
                "SEPTEMBER","OCTOBER","NOVEMBER","DECEMBER"));


//
//        // to dispaly list of courses preferred by faculty in a table
        Faculty faculty = getLoggedInFaculty();
        Set<Block> blocks = faculty.getBlocks();
        if(blocks!=null) {
            for(Block b:blocks){
                monthBlockList.remove(b.getBlockName());
            }
        }

        model.addAttribute("monthBlockList", monthBlockList);
        model.addAttribute("faculty",new Faculty());
        model.addAttribute("blocks", blocks);
        return "faculty/manageBlock";
    }

    @PostMapping("/addBlock")
    public String addUnwantedBlocks(@ModelAttribute("faculty") Faculty faculty) {
        Faculty connectedFaculty = getLoggedInFaculty();
        List<Block> blocks= new ArrayList<>();
        if (faculty != null) {
            for(String bname: faculty.getBlockSelections())
                connectedFaculty.getBlocks().add(blockService.getBlockByBlockName(bname));
//                connectedFaculty.getBlocks().addAll(faculty.getUnwantedBlocks());
            facultyService.save(connectedFaculty);
        }

        return "redirect:/faculty/block";
    }
    @GetMapping("/blockDelete/{bId}")
    public String deleteUnwantedBlocks(@PathVariable("bId") long bId) {
        Faculty faculty = getLoggedInFaculty();
        Set<Block> blocks = faculty.getBlocks();
//        for(Block b:blocks){
//            if(b.getBlockID()==bId){
//                faculty.getBlocks().remove(b);
//            }
//        }
        Iterator<Block> it = blocks.iterator();
        while (it.hasNext()) {
            Block block = it.next();
            if (block.getBlockID()==bId) {
                it.remove();
            }
        }
        facultyService.save(faculty);
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
