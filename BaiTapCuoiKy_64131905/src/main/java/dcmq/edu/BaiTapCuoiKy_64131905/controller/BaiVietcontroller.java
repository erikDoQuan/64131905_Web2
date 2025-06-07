package dcmq.edu.BaiTapCuoiKy_64131905.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.jsoup.Jsoup;
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

    private final String imageDir = System.getProperty("user.dir") + "/src/main/resources/static/images/";

 
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
        Map<String, String> truncatedContentMap = generateTruncatedContent(baiVietPage);
        model.addAttribute("baiVietPage", baiVietPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("truncatedContentMap", truncatedContentMap); 
        return "trangchu";
    }

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

        Map<String, String> truncatedContentMap = generateTruncatedContent(baiVietPage);

        model.addAttribute("baiVietPage", baiVietPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("truncatedContentMap", truncatedContentMap); 

        return "kinhnghiemnuoicho";
    }

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

        Map<String, String> truncatedContentMap = generateTruncatedContent(baiVietPage); 

        model.addAttribute("baiVietPage", baiVietPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("truncatedContentMap", truncatedContentMap);

        return "kinhnghiemnuoimeo";
    }


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

        Map<String, String> truncatedContentMap = generateTruncatedContent(baiVietPage); // thêm dòng này

        model.addAttribute("baiVietPage", baiVietPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("truncatedContentMap", truncatedContentMap); // thêm dòng này

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
                              @RequestParam("hinhAnhMoi") MultipartFile hinhAnhMoi,
                              Model model) {
        try {
          
            if (baiViet.getMaBaiViet() == null || baiViet.getMaBaiViet().isEmpty()) {
                baiViet.setMaBaiViet(UUID.randomUUID().toString().substring(0, 10));
            }

           
            if (!hinhAnhMoi.isEmpty()) {
                String tenFile = luuFileAnh(hinhAnhMoi);
                if (tenFile != null) {
                    baiViet.setHinhAnh(tenFile);
                } else {
                    baiViet.setHinhAnh("default.jpg"); 
                }
            } else {
                baiViet.setHinhAnh("default.jpg"); 
            }

          
            baiVietService.luuBaiViet(baiViet);
            return "redirect:/quanlibaiviet";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Lỗi khi lưu bài viết: " + e.getMessage());
            model.addAttribute("baiViet", baiViet);
            model.addAttribute("danhSachLoai", baiVietService.getAllLoaiBaiViet());
            return "Admin/addbaiviet"; 
        }
    }

  
    @GetMapping("/baiviet/sua/{maBaiViet}")
    public String hienThiFormSua(@PathVariable String maBaiViet, Model model) {
        BaiViet baiViet = baiVietService.layBaiVietTheoMa(maBaiViet);
        if (baiViet == null) return "redirect:/quanlibaiviet";

      
        String duongDanAnh = baiViet.getHinhAnh() != null && !baiViet.getHinhAnh().isEmpty()
                ? "/images/" + baiViet.getHinhAnh()
                : "/images/default.jpg";

        model.addAttribute("baiViet", baiViet);
        model.addAttribute("danhSachLoai", baiVietService.getAllLoaiBaiViet());
        model.addAttribute("duongDanAnh", duongDanAnh);

        return "Admin/editbaiviet";
    }

    // Xử lý cập nhật bài viết
    @PostMapping("/baiviet/capnhat")
    public String capNhatBaiViet(@ModelAttribute BaiViet baiViet,
                                 @RequestParam(value = "hinhAnhMoi", required = false) MultipartFile hinhAnhMoi) {
        if (hinhAnhMoi != null && !hinhAnhMoi.isEmpty()) {
            String tenFileMoi = luuFileAnh(hinhAnhMoi);
            if (tenFileMoi != null) {
                baiViet.setHinhAnh(tenFileMoi);
            }
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
    private Map<String, String> generateTruncatedContent(Page<BaiViet> baiVietPage) {
        Map<String, String> truncatedContentMap = new LinkedHashMap<>();
        for (BaiViet bv : baiVietPage.getContent()) {
            // Loại bỏ thẻ HTML bằng Jsoup
            String textOnly = Jsoup.parse(bv.getNoiDung()).text();
            String truncated;
            if (textOnly.length() > 150) {
                truncated = textOnly.substring(0, 150) + "...";
            } else {
                truncated = textOnly;
            }
            truncatedContentMap.put(bv.getMaBaiViet(), truncated);
        }
        return truncatedContentMap;
    }
    

    // Hàm phụ trợ lưu file ảnh
    private String luuFileAnh(MultipartFile file) {
        if (file.isEmpty()) return null;
        try {
            // Lưu trong thư mục static/images
            File dir = new File(imageDir);
            if (!dir.exists()) dir.mkdirs();

            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename != null && originalFilename.contains(".")
                    ? originalFilename.substring(originalFilename.lastIndexOf("."))
                    : "";

            String newFilename = UUID.randomUUID().toString() + extension;
            String filePath = imageDir + newFilename;
            Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            return newFilename;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
