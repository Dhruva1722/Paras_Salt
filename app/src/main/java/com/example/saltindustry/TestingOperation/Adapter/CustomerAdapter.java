//package com.example.saltindustry.TestingOperation.Adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.saltindustry.CustomAdapter;
//import com.example.saltindustry.DataModel;
//import com.example.saltindustry.R;
//
//import java.util.List;
//
//public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {
//
//private Context context;
//private List<DataModel> dataList;
//private OnEditClickListener editClickListener;
//private OnDeleteClickListener deleteClickListener;
//
//public CustomerAdapter(Context context, List<DataModel> dataList) {
//        this.context = context;
//        this.dataList = dataList;
//        }
//
//public void setOnEditClickListener(OnEditClickListener listener) {
//        this.editClickListener = listener;
//        }
//
//public void setOnDeleteClickListener(OnDeleteClickListener listener) {
//        this.deleteClickListener = listener;
//        }
//
//@NonNull
//@Override
//public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.data_list, parent, false);
//        return new ViewHolder(view);
//        }
//
//@Override
//public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        DataModel data = dataList.get(position);
//        holder.dateTextView.setText(data.getDate());
//        holder.shopNameTextView.setText(data.getShopName());
//        holder.customerNameTextView.setText(data.getCustomerName());
//        holder.productNameTextView.setText(data.getProductName());
//        holder.productQuantityTextView.setText(data.getProductQuantity());
//        holder.customerNumberTextView.setText(data.getCustomerNumber());
//        holder.priceTextView.setText(data.getPrice());
//
//        holder.deleteButton.setOnClickListener(v -> {
//        if (deleteClickListener != null) {
//        deleteClickListener.onDeleteClick(data);
//        }
//        });
//
//        holder.editButton.setOnClickListener(v -> {
//        if (editClickListener != null) {
//        editClickListener.onEditClick(data);
//        }
//        });
//}
//
//@Override
//public int getItemCount() {
//        return dataList.size();
//        }
//
//public static class ViewHolder extends RecyclerView.ViewHolder {
//    TextView dateTextView, shopNameTextView, customerNameTextView, productNameTextView,
//            productQuantityTextView, customerNumberTextView, priceTextView;
//    ImageView deleteButton, editButton;
//
//    public ViewHolder(@NonNull View itemView) {
//        super(itemView);
//        dateTextView = itemView.findViewById(R.id.date);
//        shopNameTextView = itemView.findViewById(R.id.shopName);
//        customerNameTextView = itemView.findViewById(R.id.customerName);
//        productNameTextView = itemView.findViewById(R.id.productName);
//        productQuantityTextView = itemView.findViewById(R.id.productQuantity);
//        customerNumberTextView = itemView.findViewById(R.id.customerNumber);
//        priceTextView = itemView.findViewById(R.id.price);
//        deleteButton = itemView.findViewById(R.id.deleteBtn);
//        editButton = itemView.findViewById(R.id.editBtn);
//    }
//}
//
//public interface OnEditClickListener {
//    void onEditClick(DataModel data);
//}
//
//public interface OnDeleteClickListener {
//    void onDeleteClick(DataModel data);
//}
//}