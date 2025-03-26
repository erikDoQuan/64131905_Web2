package quan.edu.TongHopGK264131905.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import quan.edu.TongHopGK264131905.model.SinhVien;




@Controller
public class Homepage {
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	@GetMapping("/about")
    public String about() {
        return "about"; 
    }
ArrayList<SinhVien> sinhViens = new ArrayList<>();
	
	public Homepage() {
		sinhViens.add(new SinhVien("SV1","Đỗ Cao Minh Quân",21,"CNTT-CLC"));
		sinhViens.add(new SinhVien("SV2", "Nguyễn Hoàng Gia Khiêm", 21, "CNTT-CLC"));
		sinhViens.add(new SinhVien("SV3", "Hà Ngọc Quốc Nam", 21, "CNTT-CLC"));
		sinhViens.add(new SinhVien("SV4", "Nguyễn Nhật Cường", 21, "Kinh Tế"));
	}
	
	
	@GetMapping("/list")
	public String list(Model model) {		
		model.addAttribute("sinhViens", sinhViens);
		return "list";
	}
	
}
