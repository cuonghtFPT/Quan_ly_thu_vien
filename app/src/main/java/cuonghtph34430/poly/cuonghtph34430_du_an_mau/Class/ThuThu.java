package cuonghtph34430.poly.cuonghtph34430_du_an_mau.Class;

public class ThuThu {
    private String matt;
    private String hoten;
    private String matkhau;
    private String loaitaikhoan;
    private String nhaplaimatkhau;

    public ThuThu(String matt, String hoten, String matkhau, String loaitaikhoan, String nhaplaimatkhau) {
        this.matt = matt;
        this.hoten = hoten;
        this.matkhau = matkhau;
        this.loaitaikhoan = loaitaikhoan;
        this.nhaplaimatkhau = nhaplaimatkhau;
    }

    public ThuThu(String matt, String hoten, String matkhau, String nhaplaimatkhau) {
        this.matt = matt;
        this.hoten = hoten;
        this.matkhau = matkhau;
        this.nhaplaimatkhau = nhaplaimatkhau;
    }

    public ThuThu() {
    }

    public String getMatt() {
        return matt;
    }

    public void setMatt(String matt) {
        this.matt = matt;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getLoaitaikhoan() {
        return loaitaikhoan;
    }

    public void setLoaitaikhoan(String loaitaikhoan) {
        this.loaitaikhoan = loaitaikhoan;
    }

    public String getNhaplaimatkhau() {
        return nhaplaimatkhau;
    }

    public void setNhaplaimatkhau(String nhaplaimatkhau) {
        this.nhaplaimatkhau = nhaplaimatkhau;
    }
}
