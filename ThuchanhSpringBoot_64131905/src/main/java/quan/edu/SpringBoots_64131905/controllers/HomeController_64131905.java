package quan.edu.SpringBoots_64131905.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController_64131905 {
	@GetMapping("/")
	public String trangChu() {
		
		 return "index";
	}
	@GetMapping("/view")
	public String gioiThieu() {
		
		 return "view";
	}
}
