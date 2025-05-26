package dcmq.edu.BaiTapCuoiKy_64131905.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import dcmq.edu.BaiTapCuoiKy_64131905.model.BaiViet;
import dcmq.edu.BaiTapCuoiKy_64131905.service.BaiVietService;

@Controller
public class BaiVietcontroller {

    @Autowired
    private BaiVietService baiVietService;

    // Trang chủ
    @GetMapping({"/", "/trangchu"})
    public String trangChu(Model model,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "6") int size,
                           @RequestParam(required = false) String keyword) {
        if (keyword == null) keyword = "";
        keyword = keyword.trim();

        Page<BaiViet> baiVietPage = keyword.isEmpty()
                ? baiVietService.getAllBaiViet(page, size)
                : baiVietService.searchBaiViet(keyword, page, size);

        model.addAttribute("baiVietPage", baiVietPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "trangchu";
    }

    // Kinh nghiệm nuôi chó
    @GetMapping("/kinhnghiemnuoicho")
    public String kinhNghiemNuoiCho(Model model,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "6") int size,
                                    @RequestParam(required = false) String keyword) {
        if (keyword == null) keyword = "";
        keyword = keyword.trim();

        Page<BaiViet> baiVietPage = keyword.isEmpty()
                ? baiVietService.getBaiVietTheoLoai("KNC", page, size)
                : baiVietService.searchBaiVietTheoLoai(keyword, "KNC", page, size);

        model.addAttribute("baiVietPage", baiVietPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "kinhnghiemnuoicho";
    }

    // Kinh nghiệm nuôi mèo
    @GetMapping("/kinhnghiemnuoimeo")
    public String hienThiKinhNghiemMeo(Model model,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "6") int size,
                                       @RequestParam(required = false) String keyword) {
        if (keyword == null) keyword = "";
        keyword = keyword.trim();

        Page<BaiViet> baiVietPage = keyword.isEmpty()
                ? baiVietService.getBaiVietTheoLoai("KNM", page, size)
                : baiVietService.searchBaiVietTheoLoai(keyword, "KNM", page, size);

        model.addAttribute("baiVietPage", baiVietPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "kinhnghiemnuoimeo";
    }

    // Chăm sóc thú cưng
    @GetMapping("/chamsocthucung")
    public String hienThiChamSocThuCung(Model model,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "6") int size,
                                        @RequestParam(required = false) String keyword) {
        if (keyword == null) keyword = "";
        keyword = keyword.trim();

        Page<BaiViet> baiVietPage = keyword.isEmpty()
                ? baiVietService.getBaiVietTheoLoai("CSTC", page, size)
                : baiVietService.searchBaiVietTheoLoai(keyword, "CSTC", page, size);

        model.addAttribute("baiVietPage", baiVietPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "chamsocthucung";
    }

    // Quản lý bài viết (admin)
    @GetMapping("/quanlibaiviet")
    public String hienThiDanhSach(Model model,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "6") int size,
                                  @RequestParam(required = false) String keyword) {
        if (keyword == null) keyword = "";
        keyword = keyword.trim();

        Page<BaiViet> baiVietPage = keyword.isEmpty()
                ? baiVietService.getAllBaiViet(page, size)
                : baiVietService.searchBaiViet(keyword, page, size);

        model.addAttribute("baiVietPage", baiVietPage);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);
        return "Admin/quanlibaiviet";
    }

    // Hiển thị form thêm
    @GetMapping("/baiviet/them")
    public String hienThiFormThem(Model model) {
        model.addAttribute("baiViet", new BaiViet());
        model.addAttribute("danhSachLoai", baiVietService.getAllLoaiBaiViet());
        return "Admin/addbaiviet";
    }

    // Xử lý thêm bài viết
    @PostMapping("/baiviet/luu")
    public String themBaiViet(@ModelAttribute BaiViet baiViet,
                              @RequestParam("hinhAnhMoi") MultipartFile hinhAnhMoi) {
        if (!hinhAnhMoi.isEmpty()) {
            String tenFile = luuFileAnh(hinhAnhMoi);
            baiViet.setHinhAnh(tenFile);
        }
        baiVietService.luuBaiViet(baiViet);
        return "redirect:/quanlibaiviet";
    }

    // Hiển thị form sửa
    @GetMapping("/baiviet/sua/{maBaiViet}")
    public String hienThiFormSua(@PathVariable String maBaiViet, Model model) {
        BaiViet baiViet = baiVietService.layBaiVietTheoMa(maBaiViet);
        if (baiViet == null) return "redirect:/quanlibaiviet";

        model.addAttribute("baiViet", baiViet);
        model.addAttribute("danhSachLoai", baiVietService.getAllLoaiBaiViet());
        return "Admin/editbaiviet";
    }

    // Xử lý cập nhật
    @PostMapping("/baiviet/capnhat")
    public String capNhatBaiViet(@ModelAttribute BaiViet baiViet,
                                 @RequestParam(value = "hinhAnhMoi", required = false) MultipartFile hinhAnhMoi) {
        if (hinhAnhMoi != null && !hinhAnhMoi.isEmpty()) {
            String tenFileMoi = luuFileAnh(hinhAnhMoi);
            baiViet.setHinhAnh(tenFileMoi);
        }
        baiVietService.luuBaiViet(baiViet);
        return "redirect:/quanlibaiviet";
    }

    // Xóa bài viết
    @GetMapping("/baiviet/xoa/{maBaiViet}")
    public String xoaBaiViet(@PathVariable String maBaiViet) {
        baiVietService.xoaBaiViet(maBaiViet);
        return "redirect:/quanlibaiviet";
    }

    // Hàm phụ trợ lưu file ảnh
    private String luuFileAnh(MultipartFile file) {
        if (file.isEmpty()) return null;
        try {
            String uploadDir = "uploads/img/";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename != null && originalFilename.contains(".")
                    ? originalFilename.substring(originalFilename.lastIndexOf("."))
                    : "";

            String newFilename = UUID.randomUUID().toString() + extension;
            String filePath = uploadDir + newFilename;
            Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            return newFilename;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
