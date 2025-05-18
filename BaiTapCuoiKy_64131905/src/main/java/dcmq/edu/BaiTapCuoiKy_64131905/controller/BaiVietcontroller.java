package dcmq.edu.BaiTapCuoiKy_64131905.controller;

import java.util.List;

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

 
    @GetMapping("/trangchu")
    public String trangChu(
        Model model,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "6") int size,
        @RequestParam(defaultValue = "") String keyword
    ) {
        Page<BaiViet> baiVietPage;

        if (keyword.isEmpty()) {
            baiVietPage = baiVietService.getAllBaiViet(page, size);
        } else {
            baiVietPage = baiVietService.searchBaiViet(keyword, page, size);
        }

        model.addAttribute("baiVietPage", baiVietPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);

        return "trangchu";
    }


    @GetMapping("/baiviet/{maBaiViet}")
    public String xemChiTietBaiViet(@PathVariable String maBaiViet, Model model) {
        BaiViet baiViet = baiVietService.layBaiVietTheoMa(maBaiViet);
        if (baiViet == null) {
            return "redirect:/trangchu";
        }
        model.addAttribute("baiViet", baiViet);
        return "xemchitiet";
    }

 
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
        Page<BaiViet> baiVietPage;

        if (!keyword.isBlank()) {
            baiVietPage = baiVietService.searchBaiViet(keyword, page, 6);
        } else {
            baiVietPage = baiVietService.getBaiVietTheoLoai("CSTC", page, 6);
        }

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
        Page<BaiViet> baiVietPage;

        if (!keyword.isBlank()) {
            baiVietPage = baiVietService.searchBaiViet(keyword, page, 6);
        } else {
            baiVietPage = baiVietService.getBaiVietTheoLoai("KNM", page, 6);
        }

        model.addAttribute("baiVietPage", baiVietPage);
        model.addAttribute("keyword", keyword);

        return "kinhnghiemnuoimeo";
    }


    @GetMapping("/quanlibaiviet")
    public String quanLyBaiViet(
        Model model,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Page<BaiViet> baiVietPage = baiVietService.getAllBaiViet(page, size);
        model.addAttribute("baiVietPage", baiVietPage);
        model.addAttribute("currentPage", page);
        return "Admin/quanlibaiviet"; 
    }



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


    @GetMapping("/baiviet/sua/{maBaiViet}")
    public String hienThiFormSua(@PathVariable String maBaiViet, Model model) {
        BaiViet baiViet = baiVietService.layBaiVietTheoMa(maBaiViet);
        if (baiViet == null) {
            return "redirect:/quanlibaiviet";
        }
        model.addAttribute("baiViet", baiViet);
        return "baiviet_form";
    }

 
    @PostMapping("/baiviet/sua")
    public String suaBaiViet(@ModelAttribute BaiViet baiViet) {
        baiVietService.luuBaiViet(baiViet);
        return "redirect:/quanlibaiviet";
    }

  
    @GetMapping("/baiviet/xoa/{maBaiViet}")
    public String xoaBaiViet(@PathVariable String maBaiViet) {
        baiVietService.xoaBaiViet(maBaiViet);
        return "redirect:/quanlibaiviet";
    }
}
