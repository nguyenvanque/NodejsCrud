package com.example.severandroidproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.severandroidproject.DAO.DaoGioHang;
import com.example.severandroidproject.GioHangAcitvity;
import com.example.severandroidproject.Model.GioHang;
import com.example.severandroidproject.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder>{
    List<GioHang> list = new ArrayList<>();
    private Context context;
    int sl = 0;
    DecimalFormat fm = new DecimalFormat("###,###,###");

    public GioHangAdapter() {
    }

    public GioHangAdapter(Context context, List<GioHang> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public GioHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phone_info, parent, false);
        return new GioHangAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GioHangAdapter.ViewHolder holder, int position) {
        final GioHang gioHang = list.get(position);
        sl = gioHang.getSoluong();
        holder.name.setText(gioHang.getPro_Name());
        holder.price.setText(gioHang.getPro_Price());
        Picasso.with(context).load(gioHang.getPro_Img()).into(holder.image);
        holder.sl.setText(gioHang.getSoluong() + "");
        final DaoGioHang daoGioHang = new DaoGioHang(context);

        holder.cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daoGioHang.sua(gioHang.getId(), (gioHang.getSoluong() + 1));
                GioHangAcitvity.list.clear();
                GioHangAcitvity.list.addAll(daoGioHang.getAll());
                GioHangAcitvity.gioHangAdapter.notifyDataSetChanged();
                holder.sl.setText(gioHang.getSoluong() + "");
                GioHangAcitvity.tong += (Integer.parseInt(gioHang.getPro_Price()));
                GioHangAcitvity.tongTien.setText(fm.format(GioHangAcitvity.tong) + " VND");
            }
        });
        holder.tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gioHang.getSoluong() != 1) {
                    daoGioHang.sua(gioHang.getId(), (gioHang.getSoluong() - 1));
                    GioHangAcitvity.list.clear();
                    GioHangAcitvity.list.addAll(daoGioHang.getAll());
                    GioHangAcitvity.gioHangAdapter.notifyDataSetChanged();
                    holder.sl.setText(gioHang.getSoluong() + "");
                    GioHangAcitvity.tong -= (Integer.parseInt(gioHang.getPro_Price()));
                    GioHangAcitvity.tongTien.setText(fm.format(GioHangAcitvity.tong) + " VND");
                }
            }
        });
        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GioHangAcitvity.tong -= (Integer.parseInt(gioHang.getPro_Price())) * gioHang.getSoluong();
                GioHangAcitvity.tongTien.setText(fm.format(GioHangAcitvity.tong) + " VND");
                daoGioHang.xoa(gioHang);
                GioHangAcitvity.list.clear();
                GioHangAcitvity.list.addAll(daoGioHang.getAll());
                GioHangAcitvity.gioHangAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name, price;
        private ImageView image, tru, cong, xoa;

        EditText sl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvNamePhoneShow);
            price = itemView.findViewById(R.id.tvPricePhoneShow);
            image = itemView.findViewById(R.id.ivLinkImgShow);
            tru = itemView.findViewById(R.id.ivTru);
            cong = itemView.findViewById(R.id.ivThem);
            xoa = itemView.findViewById(R.id.ivXoa);
            sl = itemView.findViewById(R.id.edtSoluong);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();
        }
    }
}
