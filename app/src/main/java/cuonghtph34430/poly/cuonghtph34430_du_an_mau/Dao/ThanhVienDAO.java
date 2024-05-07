package cuonghtph34430.poly.cuonghtph34430_du_an_mau.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Class.ThanhVien;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Database.DBhelper;

public class ThanhVienDAO {
    DBhelper dBhelper;

    public ThanhVienDAO(Context context) {
        dBhelper = new DBhelper(context);
    }

    public ArrayList<ThanhVien> getDSThanhVien() {
        ArrayList<ThanhVien> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dBhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THANHVIEN",null);
        if(cursor.getCount() != 0) {
            cursor.moveToFirst();
            do{
                list.add(new ThanhVien(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themThanhVien(String hoten,String namsinh) {
        SQLiteDatabase sqLiteDatabase = dBhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoten",hoten);
        contentValues.put("namsinh",namsinh);
        long check = sqLiteDatabase.insert("THANHVIEN",null,contentValues);
        if(check == -1)
            return false;
        return true;
    }

    public boolean xoaThanhVien(int matv) {
        SQLiteDatabase sqLiteDatabase = dBhelper.getWritableDatabase();
        long check = sqLiteDatabase.delete("THANHVIEN","matv = ?", new String[]{String.valueOf(matv)});
        return check != -1;
    }

}
