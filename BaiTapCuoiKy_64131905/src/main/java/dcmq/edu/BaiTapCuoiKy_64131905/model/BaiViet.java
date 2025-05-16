package dcmq.edu.BaiTapCuoiKy_64131905.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BaiViet")
public class BaiViet {
    @Id
    @Column(length = 10)
    private String maBaiViet;

    @Column(nullable = false, length = 200)
    private String tieuDe;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String noiDung;

    @Column(length = 255)
    private String hinhAnh;

    @Column(nullable = false)
    private LocalDateTime ngayDang;

    @ManyToOne
    @JoinColumn(name = "maLoai")
    private LoaiBaiViet loaiBaiViet;

    // Getters và setters
    public String getMaBaiViet() {
        return maBaiViet;
    }
    public void setMaBaiViet(String maBaiViet) {
        this.maBaiViet = maBaiViet;
    }
    public String getTieuDe() {
        return tieuDe;
    }
    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }
    public String getNoiDung() {
        return noiDung;
    }
    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }
    public String getHinhAnh() {
        return hinhAnh;
    }
    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
    public LocalDateTime getNgayDang() {
        return ngayDang;
    }
    public void setNgayDang(LocalDateTime ngayDang) {
        this.ngayDang = ngayDang;
    }
    public LoaiBaiViet getLoaiBaiViet() {
        return loaiBaiViet;
    }
    public void setLoaiBaiViet(LoaiBaiViet loaiBaiViet) {
        this.loaiBaiViet = loaiBaiViet;
    }
}
