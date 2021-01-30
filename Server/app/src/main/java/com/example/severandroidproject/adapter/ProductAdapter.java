package com.example.severandroidproject.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.severandroidproject.DAO.DaoGioHang;
import com.example.severandroidproject.DetailProductActivity;
import com.example.severandroidproject.Model.GioHang;
import com.example.severandroidproject.Model.Product;
import com.example.severandroidproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Product> listProduct;

    public ProductAdapter(Context context, ArrayList<Product> listKhoaHoc) {
        this.context = context;
        this.listProduct = listKhoaHoc;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Product product = listProduct.get(position);

        holder.txtName.setText(product.pro_Name);
        holder.txtPrice.setText(product.pro_Price);
        Picasso.with(context).load(product.pro_Img).placeholder(R.drawable.ic_launcher_background).into(holder.productIv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, DetailProductActivity.class);
//                intent.putExtra("name", product.pro_Name);
//                intent.putExtra("image", product.pro_Img);
//                intent.putExtra("price", product.pro_Price);
//                context.startActivity(intent);
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.product_details);
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
                Window window = dialog.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                if (dialog != null && dialog.getWindow() != null) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }
                TextView nameTv=dialog.findViewById(R.id.nameTv);
                TextView btnDatHang=dialog.findViewById(R.id.btnDatHang);
                TextView priceTv=dialog.findViewById(R.id.priceTv);
                ImageView productIV=dialog.findViewById(R.id.productIV);
                ImageView cancelIv=dialog.findViewById(R.id.ivClose);
                nameTv.setText(product.pro_Name);
                priceTv.setText(product.pro_Price);
                Picasso.with(context).load(product.pro_Img).placeholder(R.drawable.ic_launcher_background).into(productIV);
                cancelIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                final DaoGioHang daoGioHang = new DaoGioHang(context);
                btnDatHang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GioHang gioHang = new GioHang(0, product.catelory, product.pro_Name,
                                product.pro_Img, product.pro_Price, 1);
                        List<GioHang> listAll = daoGioHang.getAll();
                        boolean check = false;
                        int id = -1;
                        int sl = 0;
                        for (int i = 0; i < listAll.size(); i++) {
                            GioHang gioHang1 = listAll.get(i);
                            if (gioHang1.getPro_Name().contains(gioHang.getPro_Name()) &&
                                    gioHang1.getPro_Price().contains(gioHang.getPro_Price()) &&
                                    gioHang1.getPro_Img().contains(gioHang.getPro_Img())) {
                                check = true;
                                id = i;
                                sl = gioHang1.getSoluong();
                                break;
                            }
                        }
                        if (check) {
                            Toast.makeText(context, "Sản phẩm đã tồn tại!", Toast.LENGTH_SHORT).show();
//                        daoGioHang.sua(id, 4);

                        } else {
                            daoGioHang.them(gioHang);
                            Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();

                        }

                    }
                });

                dialog.show();


            }
        });



    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName, txtPrice;
        ImageView productIv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            productIv = itemView.findViewById(R.id.productIV);

        }
    }
}

