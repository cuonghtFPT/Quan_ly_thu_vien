package cuonghtph34430.poly.cuonghtph34430_du_an_mau.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Dao.ThongKeDAO;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.R;

public class Fragment_ThongKeDoanhThu extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke_doanhthu,container,false);

        EditText edtStart = view.findViewById(R.id.edtStart);
        EditText edtEnd = view.findViewById(R.id.edtEnd);
        Button btnthongKe = view.findViewById(R.id.btnThongKe);
        TextView txtketQua = view.findViewById(R.id.txtKetQua);

        Calendar calendar = Calendar.getInstance();
        edtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String ngay = "";
                        String thang = "";
                        if(dayOfMonth < 10) {
                            ngay = "0"+dayOfMonth;
                        }else {
                            ngay = String.valueOf(dayOfMonth);
                        }

                        if((month +1) <10) {
                            thang = "0" +(month+1);
                        }else {
                            thang = String.valueOf(month+1);
                        }
                        edtStart.setText(year+"/"+thang+"/"+ngay);
                    }
                },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });

        edtEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String ngay = "";
                        String thang = "";
                        if(dayOfMonth < 10) {
                            ngay = "0"+dayOfMonth;
                        }else {
                            ngay = String.valueOf(dayOfMonth);
                        }

                        if((month +1) <10) {
                            thang = "0" +(month+1);
                        }else {
                            thang = String.valueOf(month+1);
                        }
                        edtEnd.setText(year+"/"+thang+"/"+ngay);
                    }
                },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });

        btnthongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThongKeDAO thongKeDAO = new ThongKeDAO(getContext());
                String ngaybatdau = edtStart.getText().toString();
                String ngayketthuc = edtEnd.getText().toString();
                int doanhthu = thongKeDAO.getDoanhThu(ngaybatdau, ngayketthuc);
                txtketQua.setText(doanhthu + " VND");
            }
        });
        return view;
    }
}
