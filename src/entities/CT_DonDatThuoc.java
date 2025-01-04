package entities;

public class CT_DonDatThuoc {
    private String maDonDat;
    private int maThuoc;
    private float donGia;
    private float giamGia;
    private int soLuong;
    private String donViTinh;

    // Constructor có tham số
    public CT_DonDatThuoc(String maDonDat, int maThuoc, float donGia, float giamGia, int soLuong, String donViTinh) {
        super();
        this.maDonDat = maDonDat;
        this.maThuoc = maThuoc;
        this.donGia = donGia;
        this.giamGia = giamGia;
        this.soLuong = soLuong;
        this.donViTinh = donViTinh;
    }

    // Constructor mặc định
    public CT_DonDatThuoc() {
        super();
    }

    // Getter và Setter
    public String getMaDonDat() {
        return maDonDat;
    }

    public void setMaDonDat(String maDonDat) {
        this.maDonDat = maDonDat;
    }

    public int getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(int maThuoc) {
        this.maThuoc = maThuoc;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public float getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(float giamGia) {
        this.giamGia = giamGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    @Override
    public String toString() {
        return "CT_DonDatThuoc [maDonDat=" + maDonDat + ", maThuoc=" + maThuoc + ", donGia=" + donGia + ", giamGia="
                + giamGia + ", soLuong=" + soLuong + ", donViTinh=" + donViTinh + "]";
    }
}
