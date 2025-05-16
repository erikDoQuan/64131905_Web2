package dcmq.edu.BaiTapCuoiKy_64131905.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dcmq.edu.BaiTapCuoiKy_64131905.model.NguoiDung;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
    Optional<NguoiDung> findByEmail(String email);
}