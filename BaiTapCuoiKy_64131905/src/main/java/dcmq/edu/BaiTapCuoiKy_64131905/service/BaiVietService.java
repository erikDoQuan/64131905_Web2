package dcmq.edu.BaiTapCuoiKy_64131905.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dcmq.edu.BaiTapCuoiKy_64131905.model.BaiViet;
import dcmq.edu.BaiTapCuoiKy_64131905.repository.BaiVietRepository;

@Service
public class BaiVietService {

    @Autowired
    private BaiVietRepository baiVietRepository;

    public List<BaiViet> getLatestPosts() {
        // Lấy 5 bài viết mới nhất (theo ngày đăng giảm dần)
        return baiVietRepository.findTop3ByOrderByNgayDangDesc();
    }
}