package edu.miu.cs425.mumsched.controller;

import edu.miu.cs425.mumsched.domain.Course;
import edu.miu.cs425.mumsched.domain.Entry;
import edu.miu.cs425.mumsched.domain.Student;
import edu.miu.cs425.mumsched.services.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class EntryController {
    @Autowired
    EntryService entryService;
    @GetMapping("/entry")
    public String entryRegForm(@ModelAttribute("newEntry") Entry entry, Model model){
        List<Entry> entryList = new ArrayList<>(entryService.getAllEntry());
        System.out.println(entryList);
        model.addAttribute("entryList", entryList);
        model.addAttribute("newEntry", entry);
        return "admin/addEntry";
    }
    @PostMapping("/addnewentry")
    public String registerNewEntry(@ModelAttribute("newEntry") Entry entry, Model model){
        entryService.save(entry);
        return "redirect:/admin/entry";
    }
    @GetMapping("/entries")
    public String success(@ModelAttribute Entry entry, Model model){
        List<Entry> entryList = entryService.getAllEntry();
        model.addAttribute(entryList);
        return "admin/entrylist";
    }


    @GetMapping("/editentry/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {

        Entry entry = entryService.getEntryByEntryID(id);
        //.orElseThrow(()-> new IllegalArgumentException("Invalid Entry Id:" + id));
        model.addAttribute("entry",entry);
        return "admin/update-entry";
    }

    @PostMapping("/updateentry/{id}")
    public String updateentry(@PathVariable("id") long id, @Valid Entry entry, BindingResult result,
                              Model model) {
        if (result.hasErrors()) {
            entry.setEntryID(id);
            return "admin/update-entry";
        }
        entry.setEntryID(id);
        entryService.save(entry);
        model.addAttribute("entry", entryService.getAllEntry());
        return "redirect:/admin/entries";
    }
    @GetMapping("deleteentry/{id}")
    public String deleteentry(@PathVariable("id") long id, Model model) {
        Entry entry = entryService.getEntryByEntryID(id);
        //.orElseThrow(()-> new IllegalArgumentException("Invalid student Id:" + id));

        entryService.delete(entry);
        model.addAttribute("entry",entryService.getEntryByEntryID(id));
        return "redirect:/admin/entries";
    }

}
