package edu.miu.cs425.mumsched.controller;

import edu.miu.cs425.mumsched.domain.Entry;
import edu.miu.cs425.mumsched.domain.Student;
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
}
