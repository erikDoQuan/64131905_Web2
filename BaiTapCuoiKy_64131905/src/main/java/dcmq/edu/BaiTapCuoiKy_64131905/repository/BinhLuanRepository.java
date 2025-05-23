package dcmq.edu.BaiTapCuoiKy_64131905.repository;

import dcmq.edu.BaiTapCuoiKy_64131905.model.BinhLuan;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BinhLuanRepository extends JpaRepository<BinhLuan, Integer> {

    // Lấy danh sách bình luận theo mã bài viết
    List<BinhLuan> findByBaiViet_MaBaiViet(String maBaiViet);

    // Tìm kiếm bình luận theo nội dung chứa keyword, phân trang
    Page<BinhLuan> findByNoiDungContainingIgnoreCase(String keyword, Pageable pageable);
}
