package quan.edu.TruyenDuLieuSangView.model;

public class TaiKhoan {
    public String id;
    public String password;

  
    public TaiKhoan(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}