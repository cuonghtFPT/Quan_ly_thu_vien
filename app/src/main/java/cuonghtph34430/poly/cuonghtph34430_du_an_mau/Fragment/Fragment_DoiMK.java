package cuonghtph34430.poly.cuonghtph34430_du_an_mau.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Dao.ThuThuDAO;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Login.Login;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.R;

public class Fragment_DoiMK extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_doi_mk,container,false);
        EditText edtMKC = view.findViewById(R.id.pass_mk_cu);
        EditText edtMKM = view.findViewById(R.id.pass_password_mới);
        EditText edtNhapLaiMKM = view.findViewById(R.id.pass_password);
        Button btnChangepass = view.findViewById(R.id.pass_button);

        btnChangepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldpass = edtMKC.getText().toString();
                String newpass = edtMKM.getText().toString();
                String renewpass = edtNhapLaiMKM.getText().toString();
                if(newpass.equals(renewpass)) {
                    SharedPreferences sharedPreferences = requireContext().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
                    String matt = sharedPreferences.getString("matt","");
                    ThuThuDAO thuThuDAO = new ThuThuDAO(getContext());
                    boolean check = thuThuDAO.capNhatMatKhau(matt,oldpass,newpass);
                    if(check) {
                        Toast.makeText(getContext(),"Cập nhật mật khẩu thành công",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), Login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getContext(),"Cập nhật mật khẩu không thành công",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getContext(),"Nhập mật khẩu không trùng",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
