package dcmq.edu.BaiTapCuoiKy_64131905.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dcmq.edu.BaiTapCuoiKy_64131905.model.BinhLuan;
import dcmq.edu.BaiTapCuoiKy_64131905.repository.BinhLuanRepository;

import org.springframework.data.domain.PageRequest;

@Service
public class BinhLuanServiceImpl implements BinhLuanService {

    @Autowired
    private BinhLuanRepository binhLuanRepository;

    @Override
    public List<BinhLuan> layBinhLuanTheoBaiViet(String maBaiViet) {
        return binhLuanRepository.findByBaiViet_MaBaiViet(maBaiViet);
    }

    @Override
    public void luuBinhLuan(BinhLuan binhLuan) {
        binhLuanRepository.save(binhLuan);
    }

    @Override
    public BinhLuan layBinhLuanTheoId(Integer id) {
        return binhLuanRepository.findById(id).orElse(null);
    }

    @Override
    public void xoaBinhLuan(Integer id) {
        binhLuanRepository.deleteById(id);
    }

    @Override
    public Page<BinhLuan> timKiemBinhLuan(String keyword, int page) {
        int pageSize = 10; // số bản ghi mỗi trang
        Pageable pageable = PageRequest.of(page, pageSize);
        return binhLuanRepository.findByNoiDungContainingIgnoreCase(keyword, pageable);
    }
}
