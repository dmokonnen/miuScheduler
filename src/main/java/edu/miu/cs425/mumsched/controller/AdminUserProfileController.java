package edu.miu.cs425.mumsched.controller;

import edu.miu.cs425.mumsched.domain.Faculty;
import edu.miu.cs425.mumsched.domain.Student;
import edu.miu.cs425.mumsched.domain.User;
import edu.miu.cs425.mumsched.services.FacultyService;
import edu.miu.cs425.mumsched.services.StudentService;
import edu.miu.cs425.mumsched.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @author Demisew Mokonnen, Dereje Enkossa, Tsegaye Beza, Bekalu Assegid
 * @2020
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminUserProfileController {


//    @Qualifier("faculty")
//    @Autowired
//    private UserService facultyService;
//    @Qualifier("student")
    @Autowired
    private UserService userService;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private StudentService studentService;
//    private static int universityId=10103;
    @GetMapping(value="/addstudent")
    public ModelAndView addStudent(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("addStudent");
        return modelAndView;
    }

    @PostMapping(value = "/addstudent")
    public ModelAndView createStudent(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUserName(user.getUserName());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("addStudent");
        } else {
            userService.saveUser(user,"STUDENT");
            Student student = new Student();
            student.setEmail(user.getEmail());
            student.setFirstName(user.getName());
            student.setLastName(user.getLastName());
            student.setUser(user);
//            faculty.setFacultyID(universityId++);
            studentService.save(student);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("addStudent");

        }
        return modelAndView;
    }
    @GetMapping(value="/addfaculty")
    public ModelAndView addFaculty(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("addfaculty");
        return modelAndView;
    }

    @PostMapping(value = "/addfaculty")
    public ModelAndView createFaculty(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUserName(user.getUserName());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("addfaculty");
        } else {
            userService.saveUser(user,"FACULTY");
            Faculty faculty = new Faculty();
            faculty.setEmail(user.getEmail());
            faculty.setFirstName(user.getName());
            faculty.setLastName(user.getLastName());
            faculty.setUser(user);
//            faculty.setFacultyID(universityId++);
            facultyService.save(faculty);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("addfaculty");

        }
        return modelAndView;
    }

    @GetMapping(value="/home")
    public ModelAndView adminHome(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

}
