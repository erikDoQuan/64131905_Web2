package dcmq.edu.BaiTapCuoiKy_64131905.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dcmq.edu.BaiTapCuoiKy_64131905.model.BaiViet;
import dcmq.edu.BaiTapCuoiKy_64131905.repository.BaiVietRepository;

@Service
public class BaiVietService {

    @Autowired
    private BaiVietRepository baiVietRepository;

    // Lấy danh sách bài viết có phân trang
    public Page<BaiViet> getAllBaiViet(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return baiVietRepository.findAllByOrderByNgayDangDesc(pageable);
    }

    // Tìm kiếm bài viết theo từ khóa, có phân trang
    public Page<BaiViet> searchBaiViet(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return baiVietRepository.findByTieuDeContainingIgnoreCaseOrderByNgayDangDesc(keyword, pageable);
    }

    // Lấy tất cả bài viết (không phân trang) - dùng cho mục đích khác
    public List<BaiViet> getLatestPosts() {
        return baiVietRepository.findAllByOrderByNgayDangDesc(PageRequest.of(0, 5)).getContent();
    }
    public BaiViet layBaiVietTheoMa(String maBaiViet) {
        return baiVietRepository.findById(maBaiViet).orElse(null);
    }
}
