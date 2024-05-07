package cuonghtph34430.poly.cuonghtph34430_du_an_mau.Fragment;

import android.content.Intent;
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

public class Fragment_ThemNguoiDung extends Fragment {
    @Nullable
    ThuThuDAO thuThuDAO;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_them_nguoidung,container,false);

        EditText edtTenDN = view.findViewById(R.id.add_tenDN);
        EditText edtHoTen = view.findViewById(R.id.add_hoTen);
        EditText edtaddPass = view.findViewById(R.id.add_pass);
        EditText edtNhapLaiPass = view.findViewById(R.id.add_nhapLaiPass);
        Button btnLuu = view.findViewById(R.id.add_luu);
        Button btnHuy = view.findViewById(R.id.add_huy);

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenDN = edtTenDN.getText().toString();
                String hoTen = edtHoTen.getText().toString();
                String pass = edtaddPass.getText().toString();
                String passLai = edtNhapLaiPass.getText().toString();

                // Thực hiện thêm người dùng vào cơ sở dữ liệu ở đây
                if (hoTen.isEmpty() || pass.isEmpty() || passLai.isEmpty()) {
                    // Xử lý trường hợp thông tin bị thiếu
                    Toast.makeText(getActivity(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    // Gọi phương thức để thêm người dùng vào cơ sở dữ liệu
                    thuThuDAO = new ThuThuDAO(getContext());
                    boolean themThanhCong = thuThuDAO.themNguoiDung(tenDN,hoTen, passLai);
                    if (themThanhCong) {
                        Toast.makeText(getActivity(), "Thêm người dùng thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), Login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        // Xử lý khi thêm thành công (ví dụ: đóng fragment, làm mới danh sách người dùng, v.v.)
                    } else {
                        Toast.makeText(getActivity(), "Thêm người dùng thất bại", Toast.LENGTH_SHORT).show();
                        // Xử lý khi thêm thất bại
                    }
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}
