package edu.miu.cs425.mumsched.controller;

import edu.miu.cs425.mumsched.domain.Block;
import edu.miu.cs425.mumsched.domain.Entry;
import edu.miu.cs425.mumsched.services.BlockService;
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
 * @author Demisew Mokonnen, Dereje Enkossa, Tsegaye Beza, Bekalu Assegid
 * @2020
 */
@Controller
@RequestMapping("/admin")
public class BlockController {
    @Autowired
    BlockService blockService;
    @Autowired
    EntryService entryService;
    @GetMapping("/block")
    public String blockRegForm(@ModelAttribute("newBlock") Block block, Model model){
        List<String> entryNameList = new ArrayList<>();
        List<Block>  blockList = new ArrayList<>(blockService.getAllBlock());
        for (Entry entry: entryService.getAllEntry())
            entryNameList.add(entry.getEntryName());
        model.addAttribute("entryNameList",entryNameList);
        model.addAttribute("blockList",blockList);
        model.addAttribute("newBlock",block);
        return "/admin/addBlockForm";
    }
    @PostMapping("/addnewblock")
    public String registerNewBlock(@ModelAttribute("newBlock") Block block, Model model){
//        Entry entry=entryService.getEntryByEntryName(block.getEntryName());
//        entry.addBlock(block);
        blockService.save(block);
        return "redirect:/admin/block";
    }
    @GetMapping("/blocks")
    public String success(@ModelAttribute Block block, Model model){
        List<Block> blocks = blockService.getAllBlock();
        model.addAttribute("blocks",blocks);
        return "admin/blocklist";
    }
}
