package dcmq.edu.BaiTapCuoiKy_64131905.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
            Model model) {

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
            Model model) {

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



}
