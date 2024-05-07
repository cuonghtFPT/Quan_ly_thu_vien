package cuonghtph34430.poly.cuonghtph34430_du_an_mau.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Class.Sach;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Database.DBhelper;

public class ThongKeDAO {
    DBhelper dBhelper;
    public ThongKeDAO(Context context) {
        dBhelper = new DBhelper(context);
    }

    public ArrayList<Sach> getTop10() {
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dBhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT pm.masach,sc.tensach, COUNT(pm.masach) FROM PHIEUMUON pm,SACH sc WHERE pm.masach = sc.masach GROUP BY pm.masach,sc.tensach ORDER BY COUNT(pm.masach) DESC LIMIT 10",null);
        if(cursor.getCount() != 0 ) {
            cursor.moveToFirst();
            do {
                list.add(new Sach(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public int getDoanhThu(String ngaybatdau, String ngayketthuc) {
        ngaybatdau = ngaybatdau.replace("/","");
        ngayketthuc=ngayketthuc.replace("/","");
        SQLiteDatabase sqLiteDatabase = dBhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(tienthue) FROM PHIEUMUON WHERE substr(ngay,7) || substr(ngay,4,2) || substr(ngay,1,2) BETWEEN ? AND ? ",new String[]{ngaybatdau,ngayketthuc});
        if(cursor.getCount() != 0 ) {
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }
}
