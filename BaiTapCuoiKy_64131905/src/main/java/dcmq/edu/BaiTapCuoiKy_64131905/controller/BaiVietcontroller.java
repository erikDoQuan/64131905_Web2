package dcmq.edu.BaiTapCuoiKy_64131905.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dcmq.edu.BaiTapCuoiKy_64131905.model.BaiViet;
import dcmq.edu.BaiTapCuoiKy_64131905.service.BaiVietService;

@Controller
public class BaiVietcontroller {
    @Autowired
    private BaiVietService baiVietService;
    @GetMapping("/trangchu")
    public String trangChu(Model model) {
        List<BaiViet> baiVietList = baiVietService.getLatestPosts(); // lấy danh sách bài viết mới nhất
        model.addAttribute("baiVietList", baiVietList);
        return "trangchu";
    }

}
