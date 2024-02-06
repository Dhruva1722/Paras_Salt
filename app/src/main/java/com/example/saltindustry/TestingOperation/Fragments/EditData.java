//package com.example.saltindustry.TestingOperation.Fragments;
//
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.example.saltindustry.DataModel;
//import com.example.saltindustry.R;
//import com.example.saltindustry.TestingOperation.CustomerModel;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//
//public class EditData extends Fragment  {
//
//    private EditText dateEditText, shopNameEditText, customerNameEditText, customerNumberEditText, productNameEditText, productQuantityEditText, priceEditText;
//    private Button saveButton;
//
//    // Firebase
//    private DatabaseReference databaseReference;
//
//    public EditData() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//
//            View view = inflater.inflate(R.layout.fragment_edit_data, container, false);
//
//            // Initialize views
//            dateEditText = view.findViewById(R.id.dateId);
//            shopNameEditText = view.findViewById(R.id.shopNameID);
//            customerNameEditText = view.findViewById(R.id.customerNameID);
//            customerNumberEditText = view.findViewById(R.id.CustomerNumberID);
//            productNameEditText = view.findViewById(R.id.productNameID);
//            productQuantityEditText = view.findViewById(R.id.productQuantityID);
//            priceEditText = view.findViewById(R.id.priceID);
//            saveButton = view.findViewById(R.id.saveBtnID);
//
//            // Initialize Firebase
//            databaseReference = FirebaseDatabase.getInstance().getReference().child("customerData");
//
//            // Set click listener for save button
//            saveButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    saveChanges();
//                }
//            });
//
//            return view;
//        }
//
//        private void saveChanges() {
//            // Get edited data from views
//            String date = dateEditText.getText().toString().trim();
//            String shopName = shopNameEditText.getText().toString().trim();
//            String customerName = customerNameEditText.getText().toString().trim();
//            String customerNumber = customerNumberEditText.getText().toString().trim();
//            String productName = productNameEditText.getText().toString().trim();
//            String productQuantity = productQuantityEditText.getText().toString().trim();
//            String price = priceEditText.getText().toString().trim();
//
//            // Update data in Firebase
//            // Assuming you have a DataModel class to represent your data
//            CustomerModel newData = new CustomerModel(date, shopName, customerName, customerNumber, productName, productQuantity, price);
//            // Update data in Firebase
//            updateDataInFirebase(newData);
//        }
//
//    private void updateDataInFirebase(CustomerModel newData) {
//        // Update the data in Firebase with the new value
//        databaseReference.child(newData.getKey()).setValue(newData)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        // Data updated successfully
//                        Toast.makeText(getActivity(), "Data updated successfully", Toast.LENGTH_SHORT).show();
//                        // Navigate back to ViewData fragment
//                        getParentFragmentManager().popBackStack(); // Navigate back to previous fragment
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        // Failed to update data
//                        Toast.makeText(getActivity(), "Failed to update data", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
//}