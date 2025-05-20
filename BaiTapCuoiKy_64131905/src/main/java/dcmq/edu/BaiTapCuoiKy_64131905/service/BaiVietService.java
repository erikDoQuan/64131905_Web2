package dcmq.edu.BaiTapCuoiKy_64131905.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import dcmq.edu.BaiTapCuoiKy_64131905.model.BaiViet;
import dcmq.edu.BaiTapCuoiKy_64131905.model.LoaiBaiViet;
import dcmq.edu.BaiTapCuoiKy_64131905.repository.BaiVietRepository;
import dcmq.edu.BaiTapCuoiKy_64131905.repository.LoaiBaiVietRepository;

@Service
public class BaiVietService {

    @Autowired
    private BaiVietRepository baiVietRepository;
    @Autowired
    private LoaiBaiVietRepository loaiBaiVietRepository;

    public Page<BaiViet> getAllBaiViet(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("ngayDang").descending());
        return baiVietRepository.findAllByOrderByNgayDangDesc(pageable);
    }

    public Page<BaiViet> searchBaiViet(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("ngayDang").descending());
        return baiVietRepository.findByTieuDeContainingIgnoreCaseOrderByNgayDangDesc(keyword, pageable);
    }

    public List<BaiViet> getLatestPosts() {
        return baiVietRepository.findAllByOrderByNgayDangDesc(PageRequest.of(0, 5)).getContent();
    }

    public BaiViet layBaiVietTheoMa(String maBaiViet) {
        return baiVietRepository.findById(maBaiViet).orElse(null);
    }

    public Page<BaiViet> getBaiVietTheoLoai(String maLoai, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("ngayDang").descending());
        return baiVietRepository.findByLoaiBaiViet_MaLoaiOrderByNgayDangDesc(maLoai, pageable);
    }

    public List<BaiViet> getAllBaiVietNoPaging() {
        return baiVietRepository.findAllByOrderByNgayDangDesc(PageRequest.of(0, 1000)).getContent();
    }

    public BaiViet luuBaiViet(BaiViet baiViet) {
        return baiVietRepository.save(baiViet);
    }

    public void xoaBaiViet(String maBaiViet) {
        baiVietRepository.deleteById(maBaiViet);
    }
    public List<LoaiBaiViet> getAllLoaiBaiViet() {
        return loaiBaiVietRepository.findAll();
    }
    
}
