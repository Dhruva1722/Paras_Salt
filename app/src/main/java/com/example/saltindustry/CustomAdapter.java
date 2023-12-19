package com.example.saltindustry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;




public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private List<DataModel> dataList;
    private List<DataModel> originalList;
    private static OnDeleteItemClickListener deleteItemClickListener;

    public interface OnDeleteItemClickListener {
        void onDeleteItemClick(int position);
    }

    public void setOnDeleteItemClickListener(OnDeleteItemClickListener listener) {
        this.deleteItemClickListener = listener;
    }

    public CustomAdapter(Context context, List<DataModel> dataList) {
        this.context = context;
        this.dataList = dataList;
        this.originalList = new ArrayList<>(dataList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.data_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataModel data = dataList.get(position);

        holder.shopNameTextView.setText(data.getShopName());
        holder.customerNameTextView.setText(data.getCustomerName());
        holder.productNameTextView.setText(data.getProductName());
        holder.customerNumberTextView.setText(data.getCustomerNumber());
        holder.priceTextView.setText(data.getPrice());

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (deleteItemClickListener != null) {
                    deleteItemClickListener.onDeleteItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView shopNameTextView, customerNameTextView, productNameTextView, customerNumberTextView, priceTextView;
        ImageView deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            shopNameTextView = itemView.findViewById(R.id.shopName);
            customerNameTextView = itemView.findViewById(R.id.customerName);
            productNameTextView = itemView.findViewById(R.id.productName);
            customerNumberTextView = itemView.findViewById(R.id.customerNumber);
            priceTextView = itemView.findViewById(R.id.price);
            deleteButton = itemView.findViewById(R.id.deleteBtn);

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle the delete button click
                    if (deleteItemClickListener != null) {
                        deleteItemClickListener.onDeleteItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    public void filterList(List<DataModel> filteredList) {
        dataList.clear();
        dataList.addAll(filteredList);
        notifyDataSetChanged();
    }
}


//public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
//
//    private Context context;
//    private List<DataModel> dataList;
//    private List<DataModel> originalList;
//
//
//    public CustomAdapter(Context context, List<DataModel> dataList) {
//        this.context = context;
//        this.dataList = dataList;
//        this.originalList = new ArrayList<>(dataList);
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.data_list, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        DataModel data = dataList.get(position);
//
//        holder.shopNameTextView.setText(data.getShopName());
//        holder.customerNameTextView.setText(data.getCustomerName());
//        holder.productNameTextView.setText(data.getProductName());
//        holder.customerNumberTextView.setText(data.getCustomerNumber());
//        holder.priceTextView.setText(data.getPrice());
//    }
//
//    @Override
//    public int getItemCount() {
//        return dataList.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        TextView shopNameTextView, customerNameTextView, productNameTextView, customerNumberTextView, priceTextView;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            shopNameTextView = itemView.findViewById(R.id.shopName);
//            customerNameTextView = itemView.findViewById(R.id.customerName);
//            productNameTextView = itemView.findViewById(R.id.productName);
//            customerNumberTextView = itemView.findViewById(R.id.customerNumber);
//            priceTextView = itemView.findViewById(R.id.price);
//        }
//    }
//
//    public void filterList(List<DataModel> filteredList) {
//        dataList.clear();
//        dataList.addAll(filteredList);
//        notifyDataSetChanged();
//    }
//}