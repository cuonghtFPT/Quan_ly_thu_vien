package cuonghtph34430.poly.cuonghtph34430_du_an_mau.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Adapter.PhieuMuonAdapter;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Adapter.SachAdapter;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Class.LoaiSach;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Class.Sach;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Dao.LoaiSachDAO;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Dao.PhieuMuonDAO;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Dao.SachDAO;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.R;

public class Fragment_QLSach extends Fragment {
    SachDAO sachDAO;
    RecyclerView recyclerViewQLSach;
    ArrayList<Sach> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlsach,container,false);
        recyclerViewQLSach = view.findViewById(R.id.recyclerQLSach);
        FloatingActionButton floatAdd = view.findViewById(R.id.floataddSach);
        loadData();

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        return view;
    }

    private void loadData() {
        sachDAO = new SachDAO(getContext());
        list = sachDAO.getDSDauSach();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewQLSach.setLayoutManager(linearLayoutManager);
        SachAdapter adapter = new SachAdapter(list,getContext());
        recyclerViewQLSach.setAdapter(adapter);
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_sach,null);
        builder.setView(view);

        EditText edtTenSach = view.findViewById(R.id.edtTenSach);
        EditText edtTien  =view.findViewById(R.id.edtTien);
        Spinner spnLoaiSach = view.findViewById(R.id.spnloaiSach);

        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),
                getDSLoaiSach(), android.R.layout.simple_list_item_1,new String[]{"tenloai"},new int[]{android.R.id.text1});
        spnLoaiSach.setAdapter(simpleAdapter);

        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tensach = edtTenSach.getText().toString();
                int tien = Integer.parseInt(edtTien.getText().toString());
                HashMap<String,Object> hs = (HashMap<String, Object>) spnLoaiSach.getSelectedItem();
                int maloai= (int) hs.get("maloai");

                boolean check = sachDAO.getThemSach(tensach,tien,maloai);
                if(check) {
                    Toast.makeText(getContext(),"Thêm thành công",Toast.LENGTH_SHORT).show();
                    loadData();
                }else {
                    Toast.makeText(getContext(),"Thêm thất bại",Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private ArrayList<HashMap<String,Object>> getDSLoaiSach() {
        LoaiSachDAO loaiSachDAO = new LoaiSachDAO(getContext());
        ArrayList<LoaiSach> list1 = loaiSachDAO.getDSLoaiSach();
        ArrayList<HashMap<String,Object>> listHM = new ArrayList<>();

        for(LoaiSach loai: list1) {
            HashMap<String,Object> hs = new HashMap<>();
            hs.put("maloai",loai.getMaloai());
            hs.put("tenloai",loai.getTenLoai());
            listHM.add(hs);
        }
        return listHM;
    }
}
