package cuonghtph34430.poly.cuonghtph34430_du_an_mau.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Class.LoaiSach;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Database.DBhelper;

public class LoaiSachDAO {

    DBhelper dBhelper;

    public LoaiSachDAO(Context context) {
        dBhelper = new DBhelper(context);
    }


    public ArrayList<LoaiSach> getDSLoaiSach() {
        ArrayList<LoaiSach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dBhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAISACH",null);
        if(cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new LoaiSach(cursor.getInt(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themLoaiSach(LoaiSach loaiSach) {
        SQLiteDatabase sqLiteDatabase = dBhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maloai",loaiSach.getMaloai());
        contentValues.put("tenloai",loaiSach.getTenLoai());
        long check = sqLiteDatabase.insert("LOAISACH",null,contentValues);
        if(check == -1) {
            return false;
        }
        return true;
    }

    public boolean xoaLoaiSach(int maloaisach) {
        SQLiteDatabase sqLiteDatabase = dBhelper.getWritableDatabase();
        long check = sqLiteDatabase.delete("LOAISACH","maloai = ?",new String[]{String.valueOf(maloaisach)});
        return check != -1;
    }
}
