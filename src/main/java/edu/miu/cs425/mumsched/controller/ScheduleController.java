package edu.miu.cs425.mumsched.controller;

import edu.miu.cs425.mumsched.domain.Schedule;
import edu.miu.cs425.mumsched.services.CourseService;
import edu.miu.cs425.mumsched.services.EntryService;
import edu.miu.cs425.mumsched.services.ScheduleService;
import edu.miu.cs425.mumsched.utilities.ScheduleStatus;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Demisew Mokonnen
 * @2020
 */
@Controller
@RequestMapping("/admin")
public class ScheduleController {
    @Autowired
    private EntryService entryService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private CourseService courseService;

    @GetMapping("/manageSchedule")
    public String displaySchedules(Model model, @ModelAttribute("exception") String exception) {
        model.addAttribute("schedules", scheduleService.findAll());
        return "scheduleViews/manageSchedule";
    }

    @GetMapping("/createSchedule")
    public String createScheduleForm(Model model,@ModelAttribute("exception") final String exception) {
        model.addAttribute("entries", entryService.findEntryWithoutSchedule());
        model.addAttribute("schedule", new Schedule());
        model.addAttribute("exception",exception);
        return "scheduleViews/addSchedule";
    }

    @GetMapping("/editSchedule/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        Schedule schedule=scheduleService.findById(id);
        if(schedule==null)return "redirect:/admin/manageSchedule";
        model.addAttribute("schedule",schedule);
        return "scheduleViews/editSchedule";
    }

    @GetMapping("/scheduleDetails/{id}")
    public String findScheduleDetails(@PathVariable Integer id, Model model) {
        Schedule schedule=scheduleService.findById(id);
        if(schedule==null)return "redirect:/schedule/manage";
        model.addAttribute("schedule",schedule);
        return "scheduleViews/scheduleDetails";
    }

    @GetMapping("/deleteSchedule/{id}")
    public String delete(@PathVariable("id") Integer id,Model model) {
        Schedule schedule=scheduleService.findById(id);
        scheduleService.delete(schedule);
        return "redirect:/admin/manageSchedule";
    }

    @PostMapping("/createSchedule")
    public String generateSchedule(@ModelAttribute Schedule schedule, Model model) {
        scheduleService.saveOrUpdate(schedule.generate(courseService.getAllCourses()));
        model.addAttribute("message","Schedule generated");
        return "redirect:/admin/manageSchedule";
    }


    @GetMapping("/approvedSchedule/{id}")
    public String approved(@PathVariable("id") Integer id, RedirectAttributes model) {
        Schedule schedule= scheduleService.findById(id);
        if(schedule!=null) {
            schedule.setStatus(ScheduleStatus.APPROVED);
//            schedule.onApproved();
            schedule.checkIfEachSectionHasFaculty();
            scheduleService.saveOrUpdate(schedule);
        }
        return "redirect:/admin/manageSchedule";
    }

    @GetMapping("/unapprovedSchedule/{id}")
    public String unapproved(@PathVariable("id") Integer id, RedirectAttributes model) {
        Schedule schedule= scheduleService.findById(id);
        if(schedule!=null) {
            schedule.setStatus(ScheduleStatus.DRAFT);
            scheduleService.saveOrUpdate(schedule);
        }
        return "redirect:/admin/manageSchedule";
    }


}
