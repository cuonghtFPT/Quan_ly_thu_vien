package cuonghtph34430.poly.cuonghtph34430_du_an_mau;

import static cuonghtph34430.poly.cuonghtph34430_du_an_mau.R.id.mQLPhieuMuon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;

import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Fragment.Fragment_DoiMK;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Fragment.Fragment_QLLoaiSach;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Fragment.Fragment_QLPhieuMuon;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Fragment.Fragment_QLSach;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Fragment.Fragment_QLThanhVien;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Fragment.Fragment_ThemNguoiDung;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Fragment.Fragment_ThongKeDoanhThu;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Fragment.Fragment_ThongKeTop10;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Login.Login;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toobar);
        FrameLayout frameLayout = findViewById( R.id.framelayout);
        NavigationView navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawlayout);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if(item.getItemId() == R.id.mQLPhieuMuon) {
                    fragment = new Fragment_QLPhieuMuon();
                }else if(item.getItemId() == R.id.mQLLoaiSach) {
                    fragment = new Fragment_QLLoaiSach();
                } else if (item.getItemId() == R.id.mThoat) {
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else if(item.getItemId() == R.id.mQLSach) {
                    fragment = new Fragment_QLSach();
                }else if(item.getItemId() == R.id.mQLThanhVien) {
                    fragment = new Fragment_QLThanhVien();
                } else if (item.getItemId() == R.id.mDoiMatKhau) {
                    fragment = new Fragment_DoiMK();
                } else if (item.getItemId() == R.id.mTop10) {
                    fragment = new Fragment_ThongKeTop10();
                } else if (item.getItemId() == R.id.mDoanhThu) {
                    fragment = new Fragment_ThongKeDoanhThu();
                }else if(item.getItemId() == R.id.mThemNguoiDung) {
                    fragment = new Fragment_ThemNguoiDung();
                }


                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.framelayout,fragment).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                toolbar.setTitle(item.getTitle());
                return false;
            }
        });

        // Hiển thị 1 số chức năng cho admin
        SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN",MODE_PRIVATE);
        String loaiTK = sharedPreferences.getString("loaitaikhoan","");
        if(!loaiTK.equals("Admin")) {
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.mThemNguoiDung).setVisible(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}