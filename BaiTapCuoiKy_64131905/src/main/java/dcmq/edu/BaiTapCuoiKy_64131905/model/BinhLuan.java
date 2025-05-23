package dcmq.edu.BaiTapCuoiKy_64131905.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BinhLuan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer binhLuanID;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String noiDung;

    @ManyToOne
    @JoinColumn(name = "NguoiDungID", nullable = false)
    private NguoiDung nguoiDung;

    @ManyToOne
    @JoinColumn(name = "MaBaiViet", nullable = false)
    private BaiViet baiViet;

    private LocalDateTime ngayBinhLuan;

    @PrePersist
    protected void onCreate() {
        ngayBinhLuan = LocalDateTime.now();
    }

    // Getters và Setters

    public Integer getBinhLuanID() {
        return binhLuanID;
    }

    public void setBinhLuanID(Integer binhLuanID) {
        this.binhLuanID = binhLuanID;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public BaiViet getBaiViet() {
        return baiViet;
    }

    public void setBaiViet(BaiViet baiViet) {
        this.baiViet = baiViet;
    }

    public LocalDateTime getNgayBinhLuan() {
        return ngayBinhLuan;
    }

    public void setNgayBinhLuan(LocalDateTime ngayBinhLuan) {
        this.ngayBinhLuan = ngayBinhLuan;
    }
}
