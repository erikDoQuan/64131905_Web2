package dcmq.edu.BaiTapCuoiKy_64131905.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import dcmq.edu.BaiTapCuoiKy_64131905.model.BaiViet;

public interface BaiVietRepository extends JpaRepository<BaiViet, String> {

    Page<BaiViet> findAllByOrderByNgayDangDesc(Pageable pageable);

    Page<BaiViet> findByTieuDeContainingIgnoreCaseOrderByNgayDangDesc(String keyword, Pageable pageable);

    Page<BaiViet> findByLoaiBaiViet_MaLoai(String maLoai, Pageable pageable);

    Page<BaiViet> findByLoaiBaiViet_MaLoaiOrderByNgayDangDesc(String maLoai, Pageable pageable);

    // Thêm hàm tìm kiếm theo loại + từ khóa, sắp xếp ngày đăng giảm dần
    Page<BaiViet> findByLoaiBaiViet_MaLoaiAndTieuDeContainingIgnoreCaseOrderByNgayDangDesc(String maLoai, String keyword, Pageable pageable);
}
