package dcmq.edu.BaiTapCuoiKy_64131905.controller;

import dcmq.edu.BaiTapCuoiKy_64131905.model.NguoiDung;
import dcmq.edu.BaiTapCuoiKy_64131905.service.NguoiDungService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class NguoiDungController {

    @Autowired
    private NguoiDungService nguoiDungService;

    // Trang đăng nhập
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

    // Trang đăng ký
    @GetMapping("/dangky")
    public String hienThiFormDangKy() {
        return "dangky";
    }

    @PostMapping("/dangky")
    public String xuLyDangKy(@RequestParam String hoTen,
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
        nguoiDung.setRole(1); // Mặc định user

        nguoiDungService.luu(nguoiDung);
        model.addAttribute("thongBao", "Đăng ký thành công! Vui lòng đăng nhập.");
        return "dangky";
    }

    // Đăng xuất
    @GetMapping("/dangxuat")
    public String dangXuat(HttpSession session) {
        session.invalidate();
        return "redirect:/trangchu";
    }

    // Trang admin
    @GetMapping("/layoutAdmin")
    public String trangAdmin(HttpSession session, Model model) {
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
        if (nguoiDung == null || nguoiDung.getRole() != 0) {
            return "redirect:/dangnhap";
        }
        model.addAttribute("nguoiDung", nguoiDung);
        return "Admin/LayoutAdmin";
    }

    // Trang user
    @GetMapping("/layoutUser")
    public String trangUser(HttpSession session, Model model) {
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
        if (nguoiDung == null || nguoiDung.getRole() != 1) {
            return "redirect:/dangnhap";
        }
        model.addAttribute("nguoiDung", nguoiDung);
        return "LayoutUser";
    }

    // Quản lý người dùng - phân trang + tìm kiếm
    @GetMapping("/quanlinguoidung")
    public String hienThiDanhSachNguoiDung(Model model,
                                           HttpSession session,
                                           @RequestParam(name = "page", defaultValue = "0") int page,
                                           @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
        if (nguoiDung == null || nguoiDung.getRole() != 0) {
            return "redirect:/dangnhap";
        }

        Pageable pageable = PageRequest.of(page, 5);
        Page<NguoiDung> nguoiDungPage = nguoiDungService.timKiemNguoiDungPhanTrang(keyword, pageable);

        model.addAttribute("nguoiDungPage", nguoiDungPage);
        model.addAttribute("keyword", keyword);
        return "Admin/quanlinguoidung";
    }

    // GET: Hiển thị form sửa người dùng
    @GetMapping("/nguoidung/sua/{id}")
    public String hienThiFormSuaNguoiDung(@PathVariable("id") Integer id, Model model, HttpSession session) {
        NguoiDung admin = (NguoiDung) session.getAttribute("nguoiDung");
        if (admin == null || admin.getRole() != 0) {
            return "redirect:/dangnhap";
        }

        Optional<NguoiDung> nguoiDungOpt = nguoiDungService.timTheoId(id);
        if (nguoiDungOpt.isPresent()) {
            model.addAttribute("nguoiDung", nguoiDungOpt.get());
            return "Admin/editnguoidung";  // form sửa người dùng
        } else {
            model.addAttribute("loi", "Không tìm thấy người dùng.");
            return "redirect:/quanlinguoidung";
        }
    }

    // POST: Xử lý cập nhật người dùng
    @PostMapping("/nguoidung/sua")
    public String capNhatNguoiDung(@ModelAttribute("nguoiDung") NguoiDung nguoiDung,
                                   HttpSession session,
                                   Model model) {
        NguoiDung admin = (NguoiDung) session.getAttribute("nguoiDung");
        if (admin == null || admin.getRole() != 0) {
            return "redirect:/dangnhap";
        }

        // Lấy người dùng cũ từ DB để giữ mật khẩu nếu không đổi
        Optional<NguoiDung> nguoiDungCuOpt = nguoiDungService.timTheoId(nguoiDung.getNguoiDungID());
        if (nguoiDungCuOpt.isPresent()) {
            NguoiDung nguoiDungCu = nguoiDungCuOpt.get();

            // Nếu mật khẩu mới trống, giữ mật khẩu cũ
            if (nguoiDung.getMatKhau() == null || nguoiDung.getMatKhau().trim().isEmpty()) {
                nguoiDung.setMatKhau(nguoiDungCu.getMatKhau());
            }
        }

        nguoiDungService.luu(nguoiDung);
        model.addAttribute("thongBao", "Cập nhật thành công.");
        return "redirect:/quanlinguoidung";
    }

    // Xóa người dùng
    @GetMapping("/nguoidung/xoa/{id}")
    public String xoaNguoiDung(@PathVariable("id") Integer id,
                               HttpSession session) {
        NguoiDung admin = (NguoiDung) session.getAttribute("nguoiDung");
        if (admin == null || admin.getRole() != 0) {
            return "redirect:/dangnhap";
        }

        Optional<NguoiDung> nguoiDungOpt = nguoiDungService.timTheoId(id);
        if (nguoiDungOpt.isPresent()) {
            nguoiDungService.xoaTheoId(id);
        }

        return "redirect:/quanlinguoidung";
    }
}
