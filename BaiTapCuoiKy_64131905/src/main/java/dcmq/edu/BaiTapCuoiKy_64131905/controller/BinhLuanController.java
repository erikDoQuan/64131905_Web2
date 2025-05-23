package dcmq.edu.BaiTapCuoiKy_64131905.controller;

import dcmq.edu.BaiTapCuoiKy_64131905.model.BaiViet;
import dcmq.edu.BaiTapCuoiKy_64131905.model.BinhLuan;
import dcmq.edu.BaiTapCuoiKy_64131905.model.NguoiDung;
import dcmq.edu.BaiTapCuoiKy_64131905.service.BaiVietService;
import dcmq.edu.BaiTapCuoiKy_64131905.service.BinhLuanService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BinhLuanController {

    @Autowired
    private BinhLuanService binhLuanService;

    @Autowired
    private BaiVietService baiVietService;

    // Hiển thị chi tiết bài viết cùng bình luận (không phân trang)
    @GetMapping("/baiviet/{maBaiViet}")
    public String chiTietBaiViet(@PathVariable String maBaiViet, Model model) {
        BaiViet baiViet = baiVietService.layBaiVietTheoMa(maBaiViet);
        if (baiViet == null) {
            return "redirect:/trangchu";
        }

        List<BinhLuan> danhSachBinhLuan = binhLuanService.layBinhLuanTheoBaiViet(maBaiViet);

        model.addAttribute("baiViet", baiViet);
        model.addAttribute("danhSachBinhLuan", danhSachBinhLuan);
        model.addAttribute("binhLuanMoi", new BinhLuan());

        return "xemchitiet";
    }

    // Xử lý thêm bình luận mới
    @PostMapping("/baiviet/{maBaiViet}/binhluan")
    public String themBinhLuan(@PathVariable String maBaiViet,
                               @ModelAttribute("binhLuanMoi") BinhLuan binhLuan,
                               HttpSession session) {
    	NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");


        if (nguoiDung == null) {
            return "redirect:/dangnhap";
        }

        BaiViet baiViet = baiVietService.layBaiVietTheoMa(maBaiViet);
        if (baiViet == null) {
            return "redirect:/trangchu";
        }

        binhLuan.setBaiViet(baiViet);
        binhLuan.setNguoiDung(nguoiDung);

        binhLuanService.luuBinhLuan(binhLuan);

        return "redirect:/baiviet/" + maBaiViet;
    }

    // Quản lý bình luận (phân trang & tìm kiếm)
    @GetMapping("/quanlibinhluan")
    public String quanLyBinhLuan(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "") String keyword,
            Model model) {

        Page<BinhLuan> binhLuanPage = binhLuanService.timKiemBinhLuan(keyword, page);
        model.addAttribute("binhLuanPage", binhLuanPage);
        model.addAttribute("keyword", keyword);

        return "/Admin/quanlibinhluan";
    }

    @GetMapping("/binhluan/xoa/{id}")
    public String xoaBinhLuan(@PathVariable("id") Integer id,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "") String keyword) {
        binhLuanService.xoaBinhLuan(id);
        return "redirect:/quanlibinhluan?page=" + page + "&keyword=" + keyword;
    }

}
