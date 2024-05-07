package cuonghtph34430.poly.cuonghtph34430_du_an_mau.Class;

public class LoaiSach {
    private int maloai;
    private String tenLoai;

    public LoaiSach(int maloai, String tenLoai) {
        this.maloai = maloai;
        this.tenLoai = tenLoai;
    }


    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
