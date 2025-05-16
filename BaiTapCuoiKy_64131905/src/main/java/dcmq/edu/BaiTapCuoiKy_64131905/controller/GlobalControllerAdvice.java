package dcmq.edu.BaiTapCuoiKy_64131905.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


import dcmq.edu.BaiTapCuoiKy_64131905.model.NguoiDung;
import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void addThongTinNguoiDung(Model model, HttpSession session) {
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
        if (nguoiDung != null) {
            model.addAttribute("tenNguoiDung", nguoiDung.getHoTen());
        }
    }
}