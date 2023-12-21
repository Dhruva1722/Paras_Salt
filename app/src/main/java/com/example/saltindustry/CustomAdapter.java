package com.example.saltindustry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;




public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private List<DataModel> dataList;
    private List<DataModel> originalList;

    List<DataModel> filteredList = new ArrayList<>();


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
        holder.dateTextView.setText(data.getDate());
        holder.shopNameTextView.setText(data.getShopName());
        holder.customerNameTextView.setText(data.getCustomerName());
        holder.productNameTextView.setText(data.getProductName());
        holder.quantityTextView.setText(data.getProductQuantity());
        holder.customerNumberTextView.setText(data.getCustomerNumber());
        holder.priceTextView.setText(data.getPrice());

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data != null) {
                    // Get the Firebase key (unique identifier) for the item
                    String firebaseKey = data.getFirebaseKey();

                    // Call a non-static method to delete the data from Firebase
                    deleteDataFromFirebase(firebaseKey);
                } else {
                    // Log or handle the case where data is null
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView ,shopNameTextView, customerNameTextView, productNameTextView, quantityTextView , customerNumberTextView, priceTextView;
        ImageView deleteButton ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.date);
            shopNameTextView = itemView.findViewById(R.id.shopName);
            customerNameTextView = itemView.findViewById(R.id.customerName);
            productNameTextView = itemView.findViewById(R.id.productName);
            quantityTextView = itemView.findViewById(R.id.productQuantity);
            customerNumberTextView = itemView.findViewById(R.id.customerNumber);
            priceTextView = itemView.findViewById(R.id.price);

            deleteButton = itemView.findViewById(R.id.deleteBtn);

        }

    }
    private void deleteDataFromFirebase(String firebaseKey) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("ClientData");

        // Remove the data with the specified key from Firebase
        databaseReference.child(firebaseKey).removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        // Handle the success case (e.g., show a toast)
                        Toast.makeText(context, "Data deleted successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle the failure case (e.g., show an error message)
                        Toast.makeText(context, "Failed to delete data", Toast.LENGTH_SHORT).show();
                    }
                });
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