package thiGK.ntu64131905.DoCaoMinhQuan_DanhSachSinhVen.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Homecontroller {
	 @GetMapping
	    public String home() {
	        return "layoutadmin"; 
	    }
}
