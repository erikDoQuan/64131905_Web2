package dcmq.edu.BaiTapCuoiKy_64131905.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

   
    @GetMapping("/trangchu")
    public String trangChu(Model model,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "6") int size,
                           @RequestParam(defaultValue = "") String keyword) {
        Page<BaiViet> baiVietPage = keyword.isEmpty()
                ? baiVietService.getAllBaiViet(page, size)
                : baiVietService.searchBaiViet(keyword, page, size);

        model.addAttribute("baiVietPage", baiVietPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);

        return "trangchu";
    }


    @GetMapping("/kinhnghiemnuoicho")
    public String kinhNghiemNuoiCho(Model model,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "6") int size) {
        String maLoai = "KNC";
        Page<BaiViet> baiVietPage = baiVietService.getBaiVietTheoLoai(maLoai, page, size);

        model.addAttribute("baiVietPage", baiVietPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", "");

        return "kinhnghiemnuoicho";
    }

    @GetMapping("/chamsocthucung")
    public String hienThiChamSocThuCung(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "") String keyword,
                                        Model model) {
        Page<BaiViet> baiVietPage = keyword.isBlank()
                ? baiVietService.getBaiVietTheoLoai("CSTC", page, 6)
                : baiVietService.searchBaiViet(keyword, page, 6);

        model.addAttribute("baiVietPage", baiVietPage);
        model.addAttribute("keyword", keyword);

        return "chamsocthucung";
    }

    @GetMapping("/kinhnghiemnuoimeo")
    public String hienThiKinhNghiemMeo(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "") String keyword,
                                       Model model) {
        Page<BaiViet> baiVietPage = keyword.isBlank()
                ? baiVietService.getBaiVietTheoLoai("KNM", page, 6)
                : baiVietService.searchBaiViet(keyword, page, 6);

        model.addAttribute("baiVietPage", baiVietPage);
        model.addAttribute("keyword", keyword);

        return "kinhnghiemnuoimeo";
    }

 
    @GetMapping("/quanlibaiviet")
    public String hienThiDanhSach(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "6") int size,
                                  @RequestParam(defaultValue = "") String keyword,
                                  Model model) {
        Page<BaiViet> baiVietPage = keyword.isBlank()
                ? baiVietService.getAllBaiViet(page, size)
                : baiVietService.searchBaiViet(keyword, page, size);

        model.addAttribute("baiVietPage", baiVietPage);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);

        return "Admin/quanlibaiviet";
    }

    @GetMapping("/baiviet/them")
    public String hienThiFormThem(Model model) {
        model.addAttribute("baiViet", new BaiViet());
        model.addAttribute("danhSachLoai", baiVietService.getAllLoaiBaiViet());
        return "baiviet_form";
    }

    @PostMapping("/baiviet/them")
    public String themBaiViet(@ModelAttribute BaiViet baiViet) {
        baiVietService.luuBaiViet(baiViet);
        return "redirect:/quanlibaiviet";
    }

    @GetMapping("/baiviet/sua/{maBaiViet}")
    public String hienThiFormSua(@PathVariable String maBaiViet, Model model) {
        BaiViet baiViet = baiVietService.layBaiVietTheoMa(maBaiViet);
        if (baiViet == null) return "redirect:/quanlibaiviet";

        model.addAttribute("danhSachLoai", baiVietService.getAllLoaiBaiViet());
        model.addAttribute("baiViet", baiViet);
        return "Admin/editbaiviet";
    }

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

    @GetMapping("/baiviet/xoa/{maBaiViet}")
    public String xoaBaiViet(@PathVariable String maBaiViet) {
        baiVietService.xoaBaiViet(maBaiViet);
        return "redirect:/quanlibaiviet";
    }

    private String luuFileAnh(MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }
        try {
            String uploadDir = "uploads/img/";
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String originalFilename = file.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }

            String newFilename = java.util.UUID.randomUUID().toString() + extension;
            String filePath = uploadDir + newFilename;
            java.nio.file.Files.copy(file.getInputStream(), java.nio.file.Paths.get(filePath), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            return newFilename;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
