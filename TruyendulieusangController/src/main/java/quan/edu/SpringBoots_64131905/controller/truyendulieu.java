package quan.edu.SpringBoots_64131905.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.core.model.Model;

@Controller
public class truyendulieu {
    @GetMapping("/")
    public String DulieutuView(ModelMap m) {
        m.addAttribute("hovaten", "Do Cao Minh Quan");
        return "truyendulieu";
    }

    @PostMapping("/return")
    public String TDLView(@RequestParam("ten") String name, ModelMap m) {
        m.addAttribute("hotenmoi", name);
        return "ketqua"; // 
    }
}