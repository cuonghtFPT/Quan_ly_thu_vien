package cuonghtph34430.poly.cuonghtph34430_du_an_mau.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Dao.ThuThuDAO;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.MainActivity;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.R;

public class Login extends AppCompatActivity {

    EditText loginName,loginPass;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginName=findViewById(R.id.sign_username);
        loginPass=findViewById(R.id.sign_password);
        loginButton=findViewById(R.id.sign_button);

        ThuThuDAO thuThuDAO = new ThuThuDAO(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = loginName.getText().toString();
                String pass = loginPass.getText().toString();

                if(thuThuDAO.checkDangNhap(user,pass)) {
                    startActivity(new Intent(Login.this,MainActivity.class));
                    Toast.makeText(Login.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Login.this,"User và pass không đúng.Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}