package cuonghtph34430.poly.cuonghtph34430_du_an_mau.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Class.LoaiSach;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Dao.LoaiSachDAO;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.R;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder>{

    private ArrayList<LoaiSach> list;
    private Context context;

    public LoaiSachAdapter(ArrayList<LoaiSach> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public LoaiSachAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycle_loaisach,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiSachAdapter.ViewHolder holder, int position) {
        holder.txtMaLoaiSach.setText("Mã loại:"+list.get(position).getMaloai());
        holder.txtTenLoaiSach.setText("Tên loại:"+list.get(position).getTenLoai());
        holder.imgDeleteLoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa");
                builder.setIcon(R.drawable.baseline_question_mark_24);
                builder.setMessage("Bạn có chắc chắn muốn xóa loại sách này ?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int maLSCanXoa = list.get(holder.getAdapterPosition()).getMaloai();
                        LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
                        boolean kiemtra = loaiSachDAO.xoaLoaiSach(maLSCanXoa);
                        if(kiemtra) {
                            list.remove(holder.getAdapterPosition());
                            notifyDataSetChanged();
                            Toast.makeText(context,"Xóa loại sách thành công",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Xóa loại sách thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hủy thao tác xóa
                        dialog.dismiss();
                    }
                });

                // Hiển thị hộp thoại xác nhận xóa
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtMaLoaiSach,txtTenLoaiSach;
        ImageButton imgDeleteLoaiSach;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaLoaiSach = itemView.findViewById(R.id.txtMaLoaiSach);
            txtTenLoaiSach=itemView.findViewById(R.id.txtTenLoai);
            imgDeleteLoaiSach = itemView.findViewById(R.id.btnDeleteLoaiSach);
        }
    }
}

