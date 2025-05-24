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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class NguoiDungController {

    @Autowired
    private NguoiDungService nguoiDungService;

    @GetMapping("/dangnhap")
    public String hienThiFormDangNhap(Model model) {
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
                return "redirect:/quanlibaiviet";
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
    public String hienThiFormDangKy(Model model) {
        return "dangky";
    }

    @PostMapping("/dangky")
    public String xuLyDangKy(@RequestParam String hoTen,
                             @RequestParam String email,
                             @RequestParam String matKhau,
                             @RequestParam(required = false) String soDienThoai,
                             @RequestParam(required = false) String diaChi,
                             RedirectAttributes redirectAttributes) {
        if (nguoiDungService.timTheoEmail(email).isPresent()) {
            redirectAttributes.addFlashAttribute("loiDangKy", "Email đã được sử dụng.");
            return "redirect:/dangky";
        }

        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setNguoiDungID(nguoiDungService.taoMaNguoiDung());
        nguoiDung.setHoTen(hoTen);
        nguoiDung.setEmail(email);
        nguoiDung.setMatKhau(matKhau);
        nguoiDung.setSoDienThoai(soDienThoai);
        nguoiDung.setDiaChi(diaChi);
        nguoiDung.setRole(1);

        nguoiDungService.luu(nguoiDung);
        redirectAttributes.addFlashAttribute("thongBao", "Đăng ký thành công! Vui lòng đăng nhập.");
        return "redirect:/dangnhap";
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

    @GetMapping("/nguoidung/sua/{id}")
    public String hienThiFormSuaNguoiDung(@PathVariable("id") String id, Model model, HttpSession session) {
        NguoiDung admin = (NguoiDung) session.getAttribute("nguoiDung");
        if (admin == null || admin.getRole() != 0) {
            return "redirect:/dangnhap";
        }

        Optional<NguoiDung> nguoiDungOpt = nguoiDungService.timTheoId(id);
        if (nguoiDungOpt.isPresent()) {
            model.addAttribute("nguoiDung", nguoiDungOpt.get());
            return "Admin/editnguoidung";
        } else {
            model.addAttribute("loi", "Không tìm thấy người dùng.");
            return "redirect:/quanlinguoidung";
        }
    }

    @PostMapping("/nguoidung/sua")
    public String capNhatNguoiDung(@ModelAttribute("nguoiDung") NguoiDung nguoiDung,
                                   HttpSession session,
                                   Model model) {
        NguoiDung admin = (NguoiDung) session.getAttribute("nguoiDung");
        if (admin == null || admin.getRole() != 0) {
            return "redirect:/dangnhap";
        }

        Optional<NguoiDung> nguoiDungCuOpt = nguoiDungService.timTheoId(nguoiDung.getNguoiDungID());
        if (nguoiDungCuOpt.isPresent()) {
            NguoiDung nguoiDungCu = nguoiDungCuOpt.get();
            if (nguoiDung.getMatKhau() == null || nguoiDung.getMatKhau().trim().isEmpty()) {
                nguoiDung.setMatKhau(nguoiDungCu.getMatKhau());
            }
        }

        nguoiDungService.luu(nguoiDung);
        model.addAttribute("thongBao", "Cập nhật thành công.");
        return "redirect:/quanlinguoidung";
    }

    @GetMapping("/nguoidung/xoa/{id}")
    public String xoaNguoiDung(@PathVariable("id") String id, HttpSession session) {
        NguoiDung admin = (NguoiDung) session.getAttribute("nguoiDung");
        if (admin == null || admin.getRole() != 0) {
            return "redirect:/dangnhap";
        }

        Optional<NguoiDung> nguoiDungOpt = nguoiDungService.timTheoId(id);
        nguoiDungOpt.ifPresent(nd -> nguoiDungService.xoaTheoId(id));

        return "redirect:/quanlinguoidung";
    }

    @GetMapping("/nguoidung/them")
    public String hienThiFormThemNguoiDung(Model model, HttpSession session) {
        NguoiDung admin = (NguoiDung) session.getAttribute("nguoiDung");
        if (admin == null || admin.getRole() != 0) {
            return "redirect:/dangnhap";
        }
        model.addAttribute("nguoiDung", new NguoiDung());
        return "Admin/addnguoidung";
    }

    @PostMapping("/nguoidung/them")
    public String themNguoiDung(@ModelAttribute("nguoiDung") NguoiDung nguoiDung,
                                Model model,
                                HttpSession session) {
        NguoiDung admin = (NguoiDung) session.getAttribute("nguoiDung");
        if (admin == null || admin.getRole() != 0) {
            return "redirect:/dangnhap";
        }

        if (nguoiDungService.timTheoEmail(nguoiDung.getEmail()).isPresent()) {
            model.addAttribute("loi", "Email đã được sử dụng.");
            return "Admin/addnguoidung";
        }

        nguoiDung.setNguoiDungID(nguoiDungService.taoMaNguoiDung());
        nguoiDungService.luu(nguoiDung);
        return "redirect:/quanlinguoidung";
    }
}
