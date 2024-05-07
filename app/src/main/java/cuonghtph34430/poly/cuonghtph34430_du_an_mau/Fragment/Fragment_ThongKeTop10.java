package cuonghtph34430.poly.cuonghtph34430_du_an_mau.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Adapter.Top10Adapter;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Class.Sach;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Dao.ThongKeDAO;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.R;

public class Fragment_ThongKeTop10 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongketop10,container,false);
        RecyclerView recyclerViewTop10 = view.findViewById(R.id.recyclertop10);
        ThongKeDAO thongKeDAO = new ThongKeDAO(getContext());
        ArrayList<Sach> list = thongKeDAO.getTop10();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewTop10.setLayoutManager(linearLayoutManager);
        Top10Adapter adapter = new Top10Adapter(getContext(),list);
        recyclerViewTop10.setAdapter(adapter);
        return view;
    }
}
