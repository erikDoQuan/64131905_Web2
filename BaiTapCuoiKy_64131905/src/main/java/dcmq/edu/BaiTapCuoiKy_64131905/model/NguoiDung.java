package dcmq.edu.BaiTapCuoiKy_64131905.model;
import jakarta.persistence.*;

@Entity
@Table(name = "NguoiDung")
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nguoiDungID;

    @Column(nullable = false, length = 100)
    private String hoTen;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 200)
    private String matKhau;

    @Column(length = 15)
    private String soDienThoai;

    @Column(length = 200)
    private String diaChi;

    @Column(nullable = false)
    private Integer role; // 0: admin, 1: người dùng

    // Getters và setters
    public Integer getNguoiDungID() {
        return nguoiDungID;
    }
    public void setNguoiDungID(Integer nguoiDungID) {
        this.nguoiDungID = nguoiDungID;
    }
    public String getHoTen() {
        return hoTen;
    }
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMatKhau() {
        return matKhau;
    }
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    public String getSoDienThoai() {
        return soDienThoai;
    }
    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
    public String getDiaChi() {
        return diaChi;
    }
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    public Integer getRole() {
        return role;
    }
    public void setRole(Integer role) {
        this.role = role;
    }
}