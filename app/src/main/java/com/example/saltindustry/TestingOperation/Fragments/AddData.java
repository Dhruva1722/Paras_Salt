//package com.example.saltindustry.TestingOperation.Fragments;
//
//import android.app.DatePickerDialog;
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.example.saltindustry.DataModel;
//import com.example.saltindustry.R;
//import com.example.saltindustry.TestingOperation.CustomerModel;
//import com.example.saltindustry.TestingOperation.LoginScreen;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.material.textfield.TextInputEditText;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.util.Calendar;
//
//
//public class AddData extends Fragment {
//
//    Button addBtn;
//    private FirebaseAuth mAuth;
//    SharedPreferences sharedPreferences;
//    DatabaseReference databaseReference;
//
//     EditText dateEdt;
//    TextInputEditText shopNameEditText, customerNameEditText, productNameEditText, customerNumberEditText, priceEditText , productQuantityEditText ;
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        View view = inflater.inflate(R.layout.fragment_add_data, container, false);
//
//
//        mAuth = FirebaseAuth.getInstance();
//        sharedPreferences = requireActivity().getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
//        databaseReference = FirebaseDatabase.getInstance().getReference();
//
//
//        shopNameEditText = view.findViewById(R.id.shopNameID);
//        customerNameEditText = view.findViewById(R.id.customerNameID);
//        productNameEditText = view.findViewById(R.id.productNameID);
//        customerNumberEditText = view.findViewById(R.id.CustomerNumberID);
//        priceEditText = view.findViewById(R.id.priceID);
//        addBtn = view.findViewById(R.id.addBtnID);
//        productQuantityEditText = view.findViewById(R.id.productQuantityID);
//        dateEdt = view.findViewById(R.id.dateId);
//
//        dateEdt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Calendar c = Calendar.getInstance();
//
//                int year = c.get(Calendar.YEAR);
//                int month = c.get(Calendar.MONTH);
//                int day = c.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog datePickerDialog = new DatePickerDialog(
//                        requireContext(), new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year,
//                                          int monthOfYear, int dayOfMonth) {
//                        dateEdt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
//                    }
//                }, year, month, day);
//                datePickerDialog.show();
//            }
//        });
//
//        addBtn = view.findViewById(R.id.addBtnID);
//        addBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addDataToFirebase();
//            }
//        });
//
//        return view;
//    }
//
//
//
//    private void addDataToFirebase() {
//        // Get the current authenticated user
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//
//        if (currentUser != null) {
//            // Get the user's unique ID
//            String userId = currentUser.getUid();
//
//            // Get the values from EditText fields
//            String date = dateEdt.getText().toString().trim();
//            String shopName = shopNameEditText.getText().toString().trim();
//            String customerName = customerNameEditText.getText().toString().trim();
//            String productName = productNameEditText.getText().toString().trim();
//            String productQuantity = productQuantityEditText.getText().toString().trim();
//            String customerNumber = customerNumberEditText.getText().toString().trim();
//            String price = priceEditText.getText().toString().trim();
//            // Validate inputs
//            if (TextUtils.isEmpty(date) || TextUtils.isEmpty(shopName) || TextUtils.isEmpty(customerName) ||
//                    TextUtils.isEmpty(customerNumber) || TextUtils.isEmpty(productName) ||
//                    TextUtils.isEmpty(productQuantity) || TextUtils.isEmpty(price)) {
//                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            // Create a unique key for the new data
//            String dataId = databaseReference.child("customerData").push().getKey();
//
//            // Create a DataModel object with the input values
//            CustomerModel customerModel = new CustomerModel(date ,shopName, customerName, productName ,productQuantity , customerNumber, price);
//
//            dateEdt.setText("");
//            shopNameEditText.setText("");
//            customerNameEditText.setText("");
//            productNameEditText.setText("");
//            productQuantityEditText.setText("");
//            customerNumberEditText.setText("");
//            priceEditText.setText("");
//            // Save the data under the user's node in Firebase
//            databaseReference.child("Users").child(userId).child("customerData").child(dataId).setValue(customerModel)
//                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            Toast.makeText(requireContext(), "Data added successfully", Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(requireContext(), "Failed to add data", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        } else {
//            Toast.makeText(requireContext(), "User not authenticated", Toast.LENGTH_SHORT).show();
//        }
//    }
//}