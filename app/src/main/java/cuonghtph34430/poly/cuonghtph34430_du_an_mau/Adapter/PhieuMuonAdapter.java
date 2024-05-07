package cuonghtph34430.poly.cuonghtph34430_du_an_mau.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Class.PhieuMuon;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.Dao.PhieuMuonDAO;
import cuonghtph34430.poly.cuonghtph34430_du_an_mau.R;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.ViewHolder> {

    private ArrayList<PhieuMuon> list;
    private Context context;

    public PhieuMuonAdapter(ArrayList<PhieuMuon> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycle_qlpm,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaPM.setText("Mã PM:"+list.get(position).getMapm());
//        holder.txtMaTV.setText("Mã TV:"+list.get(position).getMatv());
        holder.txtTenTV.setText("Tên TV:"+list.get(position).getTentv());
//        holder.txtMaTT.setText("Mã TT:"+list.get(position).getMatt());
//        holder.txtTenTT.setText("Tên TT:"+list.get(position).getTentt());
//        holder.txtMaSach.setText("Mã Sách:"+list.get(position).getMasach());
        holder.txtTenSach.setText("Tên Sách:"+list.get(position).getTensach());
        holder.txtNgay.setText("Ngày:"+list.get(position).getNgay());
        String trangthai = "";
        if(list.get(position).getTrasach() == 1) {
            trangthai = "Đã trả sách";
            holder.btnTraSach.setVisibility(View.GONE);
        }else {
            trangthai = "Chưa trả sách";
            holder.btnTraSach.setVisibility(View.VISIBLE);
        }
        holder.txtTrangThai.setText("Trạng Thái:"+trangthai);
        holder.txtTien.setText("Tiền:"+list.get(position).getTienthue());

        holder.btnTraSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(context);
                boolean kiemtra = phieuMuonDAO.thayDoiTrangThai(list.get(holder.getAdapterPosition()).getMapm());
                if(kiemtra) {
                    list.clear();
                    list = phieuMuonDAO.getDSPhieuMuon();
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context,"Thay đổi không thành công",Toast.LENGTH_SHORT);
                }
            }
        });

        holder.btndeleteSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa");
                builder.setIcon(R.drawable.baseline_question_mark_24);
                builder.setMessage("Bạn có chắc chắn muốn xóa phiếu mượn này ?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xóa khóa học khỏi cơ sở dữ liệu
                        int maPMCanXoa = list.get(holder.getAdapterPosition()).getMapm();
                        PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(context);
                        boolean kiemtra = phieuMuonDAO.xoaPhieuMuon(maPMCanXoa);
                        if (kiemtra) {
                            // Xóa thành công, cập nhật danh sách và thông báo cho RecyclerView
                            list.remove(holder.getAdapterPosition());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Xóa phiếu mượn thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Xóa phiếu mượn không thành công", Toast.LENGTH_SHORT).show();
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
        TextView txtMaPM,txtMaTV,txtTenTV,txtMaTT,txtTenTT,txtMaSach,txtTenSach,txtNgay,txtTrangThai,txtTien;
        ImageButton btnTraSach,btndeleteSach;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaPM=itemView.findViewById(R.id.txtMaPM);
//            txtMaTV=itemView.findViewById(R.id.txtMaTV);
            txtTenTV=itemView.findViewById(R.id.txtTenTV);
//            txtMaTT=itemView.findViewById(R.id.txtMaTT);
//            txtTenTT=itemView.findViewById(R.id.txtTenTT);
//            txtMaSach=itemView.findViewById(R.id.txtMaSach);
            txtTenSach=itemView.findViewById(R.id.txtTenSach);
            txtNgay=itemView.findViewById(R.id.txtNgay);
            txtTrangThai=itemView.findViewById(R.id.txtTrangThai);
            txtTien=itemView.findViewById(R.id.txtTien);
            btnTraSach=itemView.findViewById(R.id.btnTraSSach);
            btndeleteSach=itemView.findViewById(R.id.btndeleteSach);

        }
    }
}
