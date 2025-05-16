package dcmq.edu.BaiTapCuoiKy_64131905.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import dcmq.edu.BaiTapCuoiKy_64131905.service.NguoiDungService;
import jakarta.servlet.http.HttpSession;

@Controller
public class NguoiDungController {

    @Autowired
    private NguoiDungService nguoiDungService;

    // Hiển thị form đăng nhập
    @GetMapping("/dangnhap")
    public String hienThiFormDangNhap() {
        return "dangnhap";
    }

    // Xử lý đăng nhập
    @PostMapping("/dangnhap")
    public String xuLyDangNhap(@RequestParam String email,
                               @RequestParam String matKhau,
                               HttpSession session,
                               Model model) {
        var nguoiDung = nguoiDungService.dangNhap(email, matKhau);
        if (nguoiDung.isPresent()) {
            session.setAttribute("nguoiDung", nguoiDung.get());
            return "redirect:/trangchu"; // về trang chủ
        } else {
            model.addAttribute("loiDangNhap", "Email hoặc mật khẩu không đúng");
            return "dangnhap";
        }
    }

    // Xử lý đăng xuất
    @GetMapping("/dangxuat")
    public String dangXuat(HttpSession session) {
        session.invalidate();
        return "redirect:/trangchu";
    }
}
