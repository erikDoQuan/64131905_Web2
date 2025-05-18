package dcmq.edu.BaiTapCuoiKy_64131905.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import dcmq.edu.BaiTapCuoiKy_64131905.model.BaiViet;
import dcmq.edu.BaiTapCuoiKy_64131905.service.BaiVietService;

@Controller
public class BaiVietcontroller {

    @Autowired
    private BaiVietService baiVietService;

    // Trang chủ với phân trang + tìm kiếm
    @GetMapping("/trangchu")
    public String trangChu(
        Model model,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "6") int size,
        @RequestParam(defaultValue = "") String keyword
    ) {
        Page<BaiViet> baiVietPage = keyword.isEmpty()
            ? baiVietService.getAllBaiViet(page, size)
            : baiVietService.searchBaiViet(keyword, page, size);

        model.addAttribute("baiVietPage", baiVietPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);

        return "trangchu";
    }

    // Trang chi tiết bài viết
    @GetMapping("/baiviet/{maBaiViet}")
    public String xemChiTietBaiViet(@PathVariable String maBaiViet, Model model) {
        BaiViet baiViet = baiVietService.layBaiVietTheoMa(maBaiViet);
        if (baiViet == null) return "redirect:/trangchu";

        model.addAttribute("baiViet", baiViet);
        return "xemchitiet";
    }

    // Các trang phân loại
    @GetMapping("/kinhnghiemnuoicho")
    public String kinhNghiemNuoiCho(
        Model model,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "6") int size
    ) {
        String maLoai = "KNC";
        Page<BaiViet> baiVietPage = baiVietService.getBaiVietTheoLoai(maLoai, page, size);

        model.addAttribute("baiVietPage", baiVietPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", "");

        return "kinhnghiemnuoicho";
    }

    @GetMapping("/chamsocthucung")
    public String hienThiChamSocThuCung(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "") String keyword,
        Model model
    ) {
        Page<BaiViet> baiVietPage = keyword.isBlank()
            ? baiVietService.getBaiVietTheoLoai("CSTC", page, 6)
            : baiVietService.searchBaiViet(keyword, page, 6);

        model.addAttribute("baiVietPage", baiVietPage);
        model.addAttribute("keyword", keyword);

        return "chamsocthucung";
    }

    @GetMapping("/kinhnghiemnuoimeo")
    public String hienThiKinhNghiemMeo(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "") String keyword,
        Model model
    ) {
        Page<BaiViet> baiVietPage = keyword.isBlank()
            ? baiVietService.getBaiVietTheoLoai("KNM", page, 6)
            : baiVietService.searchBaiViet(keyword, page, 6);

        model.addAttribute("baiVietPage", baiVietPage);
        model.addAttribute("keyword", keyword);

        return "kinhnghiemnuoimeo";
    }

    // Quản lý bài viết (admin)
    @GetMapping("/quanlibaiviet")
    public String hienThiDanhSach(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "6") int size,
        @RequestParam(defaultValue = "") String keyword,
        Model model
    ) {
        Page<BaiViet> baiVietPage = keyword.isBlank()
            ? baiVietService.getAllBaiViet(page, size)
            : baiVietService.searchBaiViet(keyword, page, size);

        model.addAttribute("baiVietPage", baiVietPage);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);

        return "Admin/quanlibaiviet";
    }

    // Thêm bài viết
    @GetMapping("/baiviet/them")
    public String hienThiFormThem(Model model) {
        model.addAttribute("baiViet", new BaiViet());
        return "baiviet_form";
    }

    @PostMapping("/baiviet/them")
    public String themBaiViet(@ModelAttribute BaiViet baiViet) {
        baiVietService.luuBaiViet(baiViet);
        return "redirect:/quanlibaiviet";
    }

    // Sửa bài viết
    @GetMapping("/baiviet/sua/{maBaiViet}")
    public String hienThiFormSua(@PathVariable String maBaiViet, Model model) {
        BaiViet baiViet = baiVietService.layBaiVietTheoMa(maBaiViet);
        if (baiViet == null) return "redirect:/quanlibaiviet";

        model.addAttribute("baiViet", baiViet);
        return "baiviet_form";
    }

    @PostMapping("/baiviet/sua")
    public String suaBaiViet(@ModelAttribute BaiViet baiViet) {
        baiVietService.luuBaiViet(baiViet);
        return "redirect:/quanlibaiviet";
    }

    // Xoá bài viết
    @GetMapping("/baiviet/xoa/{maBaiViet}")
    public String xoaBaiViet(@PathVariable String maBaiViet) {
        baiVietService.xoaBaiViet(maBaiViet);
        return "redirect:/quanlibaiviet";
    }
}
