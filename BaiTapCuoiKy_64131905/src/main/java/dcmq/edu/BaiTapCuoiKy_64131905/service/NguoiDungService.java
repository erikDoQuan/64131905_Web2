package dcmq.edu.BaiTapCuoiKy_64131905.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dcmq.edu.BaiTapCuoiKy_64131905.model.NguoiDung;
import dcmq.edu.BaiTapCuoiKy_64131905.repository.NguoiDungRepository;

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
}