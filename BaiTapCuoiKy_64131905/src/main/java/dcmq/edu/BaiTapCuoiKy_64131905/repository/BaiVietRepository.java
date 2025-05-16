package dcmq.edu.BaiTapCuoiKy_64131905.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import dcmq.edu.BaiTapCuoiKy_64131905.model.BaiViet;

public interface BaiVietRepository extends JpaRepository<BaiViet, String> {

 
    Page<BaiViet> findAllByOrderByNgayDangDesc(Pageable pageable);


    Page<BaiViet> findByTieuDeContainingIgnoreCaseOrderByNgayDangDesc(String keyword, Pageable pageable);
}
