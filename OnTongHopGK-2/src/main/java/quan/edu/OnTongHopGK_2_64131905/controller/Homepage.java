package quan.edu.OnTongHopGK_2_64131905.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Homepage {
	@GetMapping("/")
	public String trangChu() {
		return "frontEndViews/index";
	}
	
	@GetMapping("/about")
	public String gioiThieu() {
		return "frontEndViews/about";
	}
	 @GetMapping("/sanpham")
	    public String Trangsanpham(ModelMap model) {
		 return "frontEndViews/sanpham";
	    }

}