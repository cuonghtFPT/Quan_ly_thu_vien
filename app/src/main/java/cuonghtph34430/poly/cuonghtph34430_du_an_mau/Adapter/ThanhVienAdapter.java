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

import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Class.ThanhVien;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Dao.LoaiSachDAO;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Dao.ThanhVienDAO;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.R;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder>{

    private ArrayList<ThanhVien> list;
    private Context context;

    public ThanhVienAdapter(ArrayList<ThanhVien> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ThanhVienAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycle_thanhvien,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhVienAdapter.ViewHolder holder, int position) {
        holder.txtmaTV.setText(list.get(position).getMatv()+" ");
        holder.txttenTV.setText("Tên tv:"+list.get(position).getHoten());
        holder.txtnamSinh.setText("Năm sinh:"+list.get(position).getNamsinh());
        holder.imgDeleteTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa");
                builder.setIcon(R.drawable.baseline_question_mark_24);
                builder.setMessage("Bạn có chắc chắn muốn xóa loại sách này ?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int maTVCanXoa = list.get(holder.getAdapterPosition()).getMatv();
                        ThanhVienDAO thanhVienDAO = new ThanhVienDAO(context);
                        boolean kiemtra = thanhVienDAO.xoaThanhVien(maTVCanXoa);
                        if(kiemtra) {
                            list.remove(holder.getAdapterPosition());
                            notifyDataSetChanged();
                            Toast.makeText(context,"Xóa thành viên thành công",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Xóa thành viên thất bại thất bại",Toast.LENGTH_SHORT).show();
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

        TextView txtmaTV,txttenTV,txtnamSinh;
        ImageButton imgDeleteTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmaTV=itemView.findViewById(R.id.txtMaTV);
            txttenTV=itemView.findViewById(R.id.txtTenTV);
            txtnamSinh=itemView.findViewById(R.id.txtNamSinh);
            imgDeleteTV = itemView.findViewById(R.id.btnDeleteThanhVien);
        }
    }
}
