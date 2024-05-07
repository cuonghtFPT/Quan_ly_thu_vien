package cuonghtph34430.poly.cuonghtph34430_du_an_mau.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Class.Sach;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Database.DBhelper;

public class SachDAO {
    DBhelper dBhelper;

    public SachDAO(Context context) {
        dBhelper = new DBhelper(context);
    }

    // Lấy toàn bộ đầu sách có trong thư viện

    public ArrayList<Sach> getDSDauSach() {
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dBhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT sc.masach,sc.tensach,sc.giathue,sc.maloai,lo.tenloai FROM SACH sc,LOAISACH lo WHERE sc.maloai = lo.maloai",null);
        if(cursor.getCount() != 0) {
            cursor.moveToFirst();
            do{
               list.add(new Sach(cursor.getInt(0),
                       cursor.getString(1),
                       cursor.getInt(2),
                       cursor.getInt(3),cursor.getString(4)));
            }while (cursor.moveToNext());
        }

        return list;
    }

    public boolean getThemSach(String tensach,int giatien, int maloai) {
        SQLiteDatabase sqLiteDatabase = dBhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensach",tensach);
        contentValues.put("giathue",giatien);
        contentValues.put("maloai",maloai);
        long check =  sqLiteDatabase.insert("SACH",null,contentValues);
        if(check == -1)
            return false;
        return true;
    }

    public boolean xoaSach(int masach) {
        SQLiteDatabase sqLiteDatabase = dBhelper.getWritableDatabase();
        long check = sqLiteDatabase.delete("SACH","masach = ? ",new String[]{String.valueOf(masach)});
        return check != -1;
    }
}
