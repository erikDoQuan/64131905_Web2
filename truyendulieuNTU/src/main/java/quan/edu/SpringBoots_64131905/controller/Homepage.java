package quan.edu.SpringBoots_64131905.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class Homepage {
	@GetMapping("/view")
	public String trangChu() {
		
		 return "index";
	}
}
