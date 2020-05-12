package edu.miu.cs425.mumsched.controller;

import edu.miu.cs425.mumsched.domain.Role;
import edu.miu.cs425.mumsched.domain.User;
import edu.miu.cs425.mumsched.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Demisew Mokonnen
 * @2020
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;



    @GetMapping(value={"/", "/login"})
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping(value="/student/home")
    public ModelAndView studentHome(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Student Role");
        modelAndView.setViewName("student/student");
        return modelAndView;
    }
    @GetMapping(value="/faculty/home")
    public ModelAndView facultyHome(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Faculty Role");
        modelAndView.setViewName("faculty/faculty");
        return modelAndView;
    }
    @RequestMapping("/default")
    public String defaultAfterLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        for(Role r:user.getRoles()){
            System.out.println("The role is:"+r.getRole());
            if(r.getRole().equals("ADMIN")) {
                return "redirect:/admin/home";
            }
            else if(r.getRole().equals("STUDENT")) {
                return "redirect:/student/home";
            }
            else if(r.getRole().equals("FACULTY")) {
                return "redirect:/faculty/home";
            }
            else {
                return "redirect:/login";
            }
        }
        return "redirect:/";

    }

}
