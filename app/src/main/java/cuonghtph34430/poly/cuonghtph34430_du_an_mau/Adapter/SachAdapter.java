package cuonghtph34430.poly.cuonghtph34430_du_an_mau.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
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

import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Class.Sach;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Dao.LoaiSachDAO;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Dao.SachDAO;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.R;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder> {

    private ArrayList<Sach> list;
    private Context context;

    public SachAdapter(ArrayList<Sach> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public SachAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycle_sach,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SachAdapter.ViewHolder holder, int position) {
        holder.txtmaSach.setText("Mã sách:"+list.get(position).getMaSach());
        holder.txttenSach.setText("Tên sách:"+list.get(position).getTenSach());
        holder.txtgiaThue.setText("Giá thuê:"+list.get(position).getGiaThue());
        holder.txttenLoai.setText("Tên loại:"+list.get(position).getTenloai());
        holder.imgDeleteSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa");
                builder.setIcon(R.drawable.baseline_question_mark_24);
                builder.setMessage("Bạn có chắc chắn muốn xóa loại sách này ?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int maSach = list.get(holder.getAdapterPosition()).getMaSach();
                        SachDAO sachDAO = new SachDAO(context);
                        boolean kiemtra = sachDAO.xoaSach(maSach);
                        if(kiemtra) {
                            list.remove(holder.getAdapterPosition());
                            notifyDataSetChanged();
                            Toast.makeText(context,"Xóa  sách thành công",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Xóa sách thất bại",Toast.LENGTH_SHORT).show();
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

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtmaSach,txttenSach,txtgiaThue,txttenLoai;
        ImageButton imgDeleteSach;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmaSach = itemView.findViewById(R.id.txtMaSach);
            txttenSach=itemView.findViewById(R.id.txtTenSach);
            txtgiaThue= itemView.findViewById(R.id.txtGiaThue);
            txttenLoai=itemView.findViewById(R.id.txtTenLoai);
            imgDeleteSach=itemView.findViewById(R.id.btnDeleteSach);
        }
    }
}
