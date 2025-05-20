package dcmq.edu.BaiTapCuoiKy_64131905.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import dcmq.edu.BaiTapCuoiKy_64131905.model.NguoiDung;
import dcmq.edu.BaiTapCuoiKy_64131905.service.NguoiDungService;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class NguoiDungController {

    @Autowired
    private NguoiDungService nguoiDungService;

    @GetMapping("/dangnhap")
    public String hienThiFormDangNhap() {
        return "dangnhap";
    }

    @PostMapping("/dangnhap")
    public String xuLyDangNhap(@RequestParam String email,
                               @RequestParam String matKhau,
                               HttpSession session,
                               Model model) {
        Optional<NguoiDung> nguoiDungOpt = nguoiDungService.dangNhap(email, matKhau);
        if (nguoiDungOpt.isPresent()) {
            NguoiDung nguoiDung = nguoiDungOpt.get();
            session.setAttribute("nguoiDung", nguoiDung);

            if (nguoiDung.getRole() == 0) {
                return "redirect:/layoutAdmin";
            } else if (nguoiDung.getRole() == 1) {
                return "redirect:/layoutUser";
            } else {
                model.addAttribute("loiDangNhap", "Role không hợp lệ");
                return "dangnhap";
            }
        } else {
            model.addAttribute("loiDangNhap", "Email hoặc mật khẩu không đúng");
            return "dangnhap";
        }
    }

    @GetMapping("/dangky")
    public String hienThiFormDangKy() {
        return "dangky";
    }

    @PostMapping("/dangky")
    public String xuLyDangKy(
            @RequestParam String hoTen,
            @RequestParam String email,
            @RequestParam String matKhau,
            @RequestParam(required = false) String soDienThoai,
            @RequestParam(required = false) String diaChi,
            Model model) {

        if (nguoiDungService.timTheoEmail(email).isPresent()) {
            model.addAttribute("loi", "Email đã được sử dụng.");
            return "dangky";
        }

        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setHoTen(hoTen);
        nguoiDung.setEmail(email);
        nguoiDung.setMatKhau(matKhau);
        nguoiDung.setSoDienThoai(soDienThoai);
        nguoiDung.setDiaChi(diaChi);
        nguoiDung.setRole(1); // Mặc định role user

        nguoiDungService.luu(nguoiDung);
        model.addAttribute("thongBao", "Đăng ký thành công! Vui lòng đăng nhập.");
        return "dangky";
    }

    @GetMapping("/dangxuat")
    public String dangXuat(HttpSession session) {
        session.invalidate();
        return "redirect:/trangchu";
    }

    @GetMapping("/layoutAdmin")
    public String trangAdmin(HttpSession session, Model model) {
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
        if (nguoiDung == null || nguoiDung.getRole() != 0) {
            return "redirect:/dangnhap";
        }
        model.addAttribute("nguoiDung", nguoiDung);
        return "Admin/LayoutAdmin";
    }

    @GetMapping("/layoutUser")
    public String trangUser(HttpSession session, Model model) {
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
        if (nguoiDung == null || nguoiDung.getRole() != 1) {
            return "redirect:/dangnhap";
        }
        model.addAttribute("nguoiDung", nguoiDung);
        return "LayoutUser";
    }

    @GetMapping("/quanlinguoidung")
    public String hienThiDanhSachNguoiDung(
            Model model,
            HttpSession session,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword) {

        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
        if (nguoiDung == null || nguoiDung.getRole() != 0) {
            return "redirect:/dangnhap";
        }

        Pageable pageable = PageRequest.of(page, 5); // 5 bản ghi trên trang
        Page<NguoiDung> nguoiDungPage = nguoiDungService.timKiemNguoiDungPhanTrang(keyword, pageable);

        model.addAttribute("nguoiDungPage", nguoiDungPage);
        model.addAttribute("keyword", keyword);
        return "Admin/quanlinguoidung";
    }

}
