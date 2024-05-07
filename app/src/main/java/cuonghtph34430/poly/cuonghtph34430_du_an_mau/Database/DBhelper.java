package cuonghtph34430.poly.cuonghtph34430_du_an_mau.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DANGKYMONHOC";
    private static final int DATABASE_VERSION = 1;

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String dbThuThu = "CREATE TABLE THUTHU (matt text primary key, hoten text, matkhau text, loaitaikhoan text,nhaplaimatkhau text)";
        db.execSQL(dbThuThu);

        String dbThanhVien = "CREATE TABLE THANHVIEN (matv integer primary key autoincrement, hoten text, namsinh text)";
        db.execSQL(dbThanhVien);

        String dbLoaiSach = "CREATE TABLE LOAISACH (maloai integer primary key autoincrement, tenloai text)";
        db.execSQL(dbLoaiSach);

        String dbSach = "CREATE TABLE SACH (masach integer primary key autoincrement, tensach text, giathue integer , maloai integer references  LOAISACH(maloai))";
        db.execSQL(dbSach);

        String dbPhieuMuon = "CREATE TABLE PHIEUMUON (mapm integer primary key autoincrement, matv integer references THANHVIEN(matv), matt text references THUTHU(matt) , masach integer references  SACH(masach),ngay text,trasach integer , tienthue integer)";
        db.execSQL(dbPhieuMuon);

        db.execSQL("INSERT INTO LOAISACH VALUES(1,'Hành động'),(2,'Tình cảm'),(3,'Giáo khoa')");
        db.execSQL("INSERT INTO SACH VALUES(1,'Kẻ hủy diệt',2500,1),(2,'Titanic',1000,1),(3,'Lập trình android',2000,3)");
        db.execSQL("INSERT INTO THUTHU VALUES('admin','Nguyễn Văn Anh','admin','Admin','admin'),('thuthu01','Trần Văn Hùng','abc123','Thủ thư','abc123'),('thuthu02','Cao thi lieu','123abc','Thủ thư','123abc')");
        db.execSQL("INSERT INTO THANHVIEN VALUES(1,'Hoàng Trọng Cường','2000'),(2,'Nguyễn Viết Mạnh','2000')");
        db.execSQL(("INSERT INTO PHIEUMUON VALUES(1,1,'thuthu01',1,'19/03/2023',1,2500),(2,1,'thuthu01',3,'20/03/2023',0,2000),(3,2,'thuthu02',1,'21/02/2023',1,2000)"));


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS THUTHU");
            db.execSQL("DROP TABLE IF EXISTS THANHVIEN");
            db.execSQL("DROP TABLE IF EXISTS LOAISACH");
            db.execSQL("DROP TABLE IF EXISTS SACH");
            db.execSQL("DROP TABLE IF EXISTS PHIEUMUON");
            onCreate(db);
        }

    }
}
