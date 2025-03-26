package quan.edu.TongHopGK264131905.model;

public class SinhVien {
    public String maSV;
    public String hoTen;
    public int tuoi;
    public String nganh;

    public SinhVien(String maSV, String hoTen, int tuoi, String nganh) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.tuoi = tuoi;
        this.nganh = nganh;
    }

    public String getMaSV() { return maSV; }
    public void setMaSV(String maSV) { this.maSV = maSV; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public int getTuoi() { return tuoi; }
    public void setTuoi(int tuoi) { this.tuoi = tuoi; }

    public String getNganh() { return nganh; }
    public void setNganh(String nganh) { this.nganh = nganh; }
}
