package com.example.dn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_ngay extends RecyclerView.Adapter<Adapter_ngay.ViewHolder>{
    ArrayList<Custom_ngay> custom_ngays;
    Context context;

    public Adapter_ngay(ArrayList<Custom_ngay> custom_ngays, Context context) {
        this.custom_ngays = custom_ngays;
        this.context = context;
    }

    public Adapter_ngay() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //tạo 1 layout cho nó
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        //tạo 1 view
        View itemView=layoutInflater.inflate(R.layout.item_ngay,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //gán dữ liệu vào textview
        holder.thu.setText(custom_ngays.get(position).getThu());
        holder.mua.setText(custom_ngays.get(position).getMua());
        holder.ndmax.setText(custom_ngays.get(position).getNdmax());
        holder.ndmin.setText(custom_ngays.get(position).getNdmin());
    }

    @Override
    public int getItemCount() {
        return custom_ngays.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView thu,mua,ndmax,ndmin;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            thu=itemView.findViewById(R.id.thu);
            mua=itemView.findViewById(R.id.mua);
            ndmax=itemView.findViewById(R.id.ndmax);
            ndmin=itemView.findViewById(R.id.ndmin);
        }
    }
}
