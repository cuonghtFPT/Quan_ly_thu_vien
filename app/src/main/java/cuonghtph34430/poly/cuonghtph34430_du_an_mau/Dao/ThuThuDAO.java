package cuonghtph34430.poly.cuonghtph34430_du_an_mau.Dao;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Class.PhieuMuon;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Database.DBhelper;

public class ThuThuDAO {
    DBhelper dBhelper;
    SharedPreferences sharedPreferences;

    public ThuThuDAO(Context context) {
        dBhelper = new DBhelper(context);
        sharedPreferences =context.getSharedPreferences("THONGTIN",MODE_PRIVATE);
    }

    // Đăng nhập
    public boolean checkDangNhap(String matt,String matkhau) {
        SQLiteDatabase sqLiteDatabase = dBhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE matt = ? AND matkhau = ?",new String[]{matt,matkhau});
        if(cursor.getCount() != 0) {
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("matt",cursor.getString(0));
            editor.putString("loaitaikhoan",cursor.getString(3));
            editor.commit();
            return true;
        }else {
            return false;
        }
    }

    public boolean capNhatMatKhau(String username, String oldPass, String newPass) {
        SQLiteDatabase sqLiteDatabase = dBhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE matt = ? AND matkhau = ?", new String[]{username,oldPass});
        if(cursor.getCount() > 0 ) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("matkhau", newPass);
            long check = sqLiteDatabase.update("THUTHU",contentValues,"matt=?", new String[]{username});
            if(check == -1)
                return false;
            return true;
        }
        return false;
    }

    public boolean themNguoiDung(String matt, String hoten, String pass) {
        SQLiteDatabase sqLiteDatabase = dBhelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("matt", matt);
        contentValues.put("hoten", hoten);
        contentValues.put("matkhau", pass);
        contentValues.put("loaitaikhoan", "thuthu"); // Đặt vai trò người dùng là thủ thư

        long check = sqLiteDatabase.insert("THUTHU", null, contentValues);

        // Kiểm tra kết quả thêm và trả về true nếu thành công, ngược lại trả về false
        return check != -1;
    }
}
