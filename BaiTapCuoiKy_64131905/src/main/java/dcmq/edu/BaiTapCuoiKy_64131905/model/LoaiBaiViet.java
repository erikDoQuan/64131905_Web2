package dcmq.edu.BaiTapCuoiKy_64131905.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LoaiBaiViet")
public class LoaiBaiViet {
    @Id
    @Column(length = 10)
    private String maLoai;

    @Column(nullable = false, length = 100)
    private String tenLoai;

    @Column(columnDefinition = "LONGTEXT")
    private String moTa;

    // Getters và setters
    public String getMaLoai() {
        return maLoai;
    }
    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }
    public String getTenLoai() {
        return tenLoai;
    }
    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
    public String getMoTa() {
        return moTa;
    }
    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}

