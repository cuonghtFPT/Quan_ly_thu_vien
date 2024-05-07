package cuonghtph34430.poly.cuonghtph34430_du_an_mau.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import cuonghtph34430.poly.cuonghtph34430_du_an_mau.R;

public class Welcom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Welcom.this, Login.class);
                startActivity(intent);
            }
        },3000);
    }
}