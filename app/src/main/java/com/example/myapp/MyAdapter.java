package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    Context context;
    ArrayList<Item> items = new ArrayList<Item>();
    static OnItemClickListener listener;

    public MyAdapter(Context context, ArrayList<Item> items){
        this.context = context;
        this.items = items;

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public Item getItem(int position) {
        return items.get(position);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.setItem(item);
    }

    public void addItem(Item item){
        items.add(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_model;
        TextView txt_cat;
        TextView txt_channel;
        TextView txt_capacity;
        TextView txt_grade;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_model = itemView.findViewById(R.id.item_model);
            txt_cat = itemView.findViewById(R.id.item_cat);
            txt_channel = itemView.findViewById(R.id.item_channel);
            txt_capacity = itemView.findViewById(R.id.item_capacity);
            txt_grade = itemView.findViewById(R.id.item_grade);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null){
                        listener.onItemClick(ViewHolder.this, v, position);
                    }
                }
            });
        }

        public void setItem(Item item){
            txt_model.setText(txtChange(item.getModel()));
            txt_cat.setText(txtChange(item.getCategory()));
            txt_channel.setText(txtChange(item.getChannel()));
            txt_capacity.setText(txtChange(item.getCapacity()));
            txt_grade.setText(txtChange(item.getGrade()));

        }
    }
    // 공백 확인
    public static String txtChange(String str){
        if (str.equals("")){
            str = "empty";
        }
        return str;
    }

    // 리스너
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public static interface OnItemClickListener{
        public void onItemClick(ViewHolder holder, View view, int position);
    }
}