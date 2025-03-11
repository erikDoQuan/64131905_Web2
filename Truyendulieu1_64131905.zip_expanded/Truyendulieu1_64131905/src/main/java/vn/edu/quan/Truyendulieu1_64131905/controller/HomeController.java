package vn.edu.quan.Truyendulieu1_64131905.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import vn.edu.quan.Truyendulieu1_64131905.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/truyenObject")
    public String hamDanhSachSinhVien(ModelMap model) {

        List<SinhVien> danhSachSinhVien = new ArrayList<>();

        SinhVien sv1 = new SinhVien("64131905", "Đỗ Cao Minh Quân", 2004, "Nam");
        SinhVien sv2 = new SinhVien("64131900", "Nguyễn Hoàng Gia Khiêm", 2004, "Nam");
        SinhVien sv3 = new SinhVien("64131323", "Hà Ngọc Quốc Nam", 2004, "Nam");

        danhSachSinhVien.add(sv1);
        danhSachSinhVien.add(sv2);
        danhSachSinhVien.add(sv3);

        model.addAttribute("danhSachSinhVien", danhSachSinhVien);

        return "sinhvien";
    }
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, ModelMap model) {
        String id = request.getParameter("id");
        String password = request.getParameter("password");

   
        if ("admin".equals(id) && "123456".equals(password)) {
            model.addAttribute("message", "Đăng nhập thành công!");
            return "home"; 
        } else {
            model.addAttribute("message", "Sai tài khoản hoặc mật khẩu!");
            return "login";
        }
    }
}
