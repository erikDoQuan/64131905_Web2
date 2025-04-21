package quan.edu.ThiGK_Lamlai_64131905.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homecontroller {
    @RequestMapping("/")
    public String dashboard(ModelMap model) {
        model.addAttribute("mssv", "64131905");
        model.addAttribute("hoVaTen", "Đỗ Cao Minh Quân");
        model.addAttribute("namSinh", "64131905");
        model.addAttribute("gioiTinh", "Nam");
        return "dashboard";
    }

}