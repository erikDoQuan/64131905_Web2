package quan.edu.SpringBoots_64131905.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class HomeController {

    @GetMapping("/bmi")
    public String hienThiForm() {
        return "bmi"; 
    }

    @PostMapping("/bmi")
    public String tinhBMI(@RequestParam("canNang") double canNang,
                          @RequestParam("chieuCao") double chieuCao,
                          Model model) {
        double bmi = canNang / (chieuCao * chieuCao);
        bmi = Math.round(bmi * 10.0) / 10.0; 
        
        String phanLoai;
        if (bmi < 18.5) {
            phanLoai = "Gầy";
        } else if (bmi >= 18.5 && bmi < 25) {
            phanLoai = "Bình thường";
        } else if (bmi >= 25 && bmi < 30) {
            phanLoai = "Thừa cân";
        } else {
            phanLoai = "Béo phì";
        }
        
        model.addAttribute("bmi", bmi);
        model.addAttribute("phanLoai", phanLoai);
        return "bmi"; 
    }
}
