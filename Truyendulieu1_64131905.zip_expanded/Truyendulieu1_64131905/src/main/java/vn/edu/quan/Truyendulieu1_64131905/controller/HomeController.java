package vn.edu.quan.Truyendulieu1_64131905.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import vn.edu.quan.Truyendulieu1_64131905.model.SinhVien;


@Controller
public class HomeController {

    @GetMapping("/truyenObject")
    public String hamSinhVien(ModelMap model) {
  
        SinhVien sv = new SinhVien();
        sv.setMssv("64131905");
        sv.setHoTen("Đỗ Cao Minh Quân");
        sv.setNamSinh(2004);
        sv.setGioiTinh("Nam");
        model.addAttribute("sv", sv);
      
        model.addAttribute("MSSV", sv.getMssv());
        model.addAttribute("HoTen", sv.getHoTen());
        model.addAttribute("NamSinh", sv.getNamSinh());
        model.addAttribute("GioiTinh", sv.getGioiTinh());

        return "sinhvien"; 
    }
}
