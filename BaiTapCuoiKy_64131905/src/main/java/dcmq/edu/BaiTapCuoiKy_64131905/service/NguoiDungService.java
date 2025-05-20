package dcmq.edu.BaiTapCuoiKy_64131905.service;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dcmq.edu.BaiTapCuoiKy_64131905.model.NguoiDung;
import dcmq.edu.BaiTapCuoiKy_64131905.repository.NguoiDungRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class NguoiDungService {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    public Optional<NguoiDung> dangNhap(String email, String matKhau) {
        Optional<NguoiDung> nguoiDungOpt = nguoiDungRepository.findByEmail(email);
        if (nguoiDungOpt.isPresent() && nguoiDungOpt.get().getMatKhau().equals(matKhau)) {
            return nguoiDungOpt;
        }
        return Optional.empty();
    }

    public NguoiDung luu(NguoiDung nguoiDung) {
        return nguoiDungRepository.save(nguoiDung);
    }

    public Optional<NguoiDung> timTheoEmail(String email) {
        return nguoiDungRepository.findByEmail(email);
    }

    // Trả về Page<NguoiDung> phân trang và tìm kiếm theo tên
    public Page<NguoiDung> timKiemNguoiDungPhanTrang(String keyword, Pageable pageable) {
        if (keyword == null || keyword.isEmpty()) {
            return nguoiDungRepository.findAllRole0Or1(pageable);
        }
        return nguoiDungRepository.findAllRole0Or1AndHoTenContainingIgnoreCase(keyword, pageable);
    }


    public List<NguoiDung> findAll() {
        return nguoiDungRepository.findAll();
    }
    public Optional<NguoiDung> timTheoId(int id) {
        return nguoiDungRepository.findById(id);
    }

    public void xoaTheoId(int id) {
        nguoiDungRepository.deleteById(id);
    }

}
