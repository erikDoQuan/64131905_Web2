package dcmq.edu.BaiTapCuoiKy_64131905.service;

import java.util.List;


public interface BinhLuanService {
    List<BinhLuan> layTatCaBinhLuan();
    List<BinhLuan> layBinhLuanTheoBaiViet(String maBaiViet);
    BinhLuan themBinhLuan(BinhLuan binhLuan);
}
