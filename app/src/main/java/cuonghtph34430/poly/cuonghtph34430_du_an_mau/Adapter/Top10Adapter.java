package cuonghtph34430.poly.cuonghtph34430_du_an_mau.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Class.Sach;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.R;

public class Top10Adapter extends RecyclerView.Adapter<Top10Adapter.ViewHolder> {

    private Context context;
    private ArrayList<Sach> list;

    public Top10Adapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_top10,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txttopMaSach.setText("Mã sách:"+String.valueOf(list.get(position).getMaSach()));
        holder.txttopTenSach.setText("Tên sách:"+list.get(position).getTenSach());
        holder.txttopSoLuongMuon.setText("Số lượng mượn:"+String.valueOf(list.get(position).getSoLuongDaMuon()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txttopMaSach,txttopTenSach,txttopSoLuongMuon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttopMaSach=itemView.findViewById(R.id.txtTopmasach);
            txttopTenSach=itemView.findViewById(R.id.txtToptensach);
            txttopSoLuongMuon=itemView.findViewById(R.id.txtTopsoluongmuon);
        }
    }
}
