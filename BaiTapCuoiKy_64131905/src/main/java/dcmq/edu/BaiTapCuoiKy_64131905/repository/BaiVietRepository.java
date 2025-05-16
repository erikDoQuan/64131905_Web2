package dcmq.edu.BaiTapCuoiKy_64131905.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dcmq.edu.BaiTapCuoiKy_64131905.model.BaiViet;

public interface BaiVietRepository extends JpaRepository<BaiViet, String> {
    List<BaiViet> findTop3ByOrderByNgayDangDesc();
}
