package dcmq.edu.BaiTapCuoiKy_64131905.service;

import java.util.List;

import org.springframework.data.domain.Page;

import dcmq.edu.BaiTapCuoiKy_64131905.model.BinhLuan;

public interface BinhLuanService {

    List<BinhLuan> layBinhLuanTheoBaiViet(String maBaiViet);

    void luuBinhLuan(BinhLuan binhLuan);

    BinhLuan layBinhLuanTheoId(Integer id);

    void xoaBinhLuan(Integer id);

    Page<BinhLuan> timKiemBinhLuan(String keyword, int page);
    
}
