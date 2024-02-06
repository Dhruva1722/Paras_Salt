package com.example.saltindustry;

import android.app.DatePickerDialog;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.Calendar;

public class EditDataDialogFragment extends DialogFragment {

    private EditText dateEditText, shopNameEditText, customerNameEditText, customerNumberEditText, productNameEditText, productQuantityEditText, priceEditText;
    private Button saveButton;

    private DatabaseReference databaseReference;
    private DataModel existingData;

    public static EditDataDialogFragment newInstance(String firebaseKey, String date, String shopName, String customerName, String customerNumber, String productName, String productQuantity, String price) {
        EditDataDialogFragment fragment = new EditDataDialogFragment();
        Bundle args = new Bundle();
        args.putString("firebaseKey", firebaseKey);
        args.putString("date", date);
        args.putString("shopName", shopName);
        args.putString("customerName", customerName);
        args.putString("customerNumber", customerNumber);
        args.putString("productName", productName);
        args.putString("productQuantity", productQuantity);
        args.putString("price", price);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_data_dialog, container, false);
        if (getArguments() != null) {
            existingData = (DataModel) getArguments().getSerializable("data");
        }

        shopNameEditText = view.findViewById(R.id.shopNameID);
        customerNameEditText = view.findViewById(R.id.customerNameID);
        customerNumberEditText = view.findViewById(R.id.CustomerNumberID);
        productNameEditText = view.findViewById(R.id.productNameID);
        productQuantityEditText = view.findViewById(R.id.productQuantityID);
        priceEditText = view.findViewById(R.id.priceID);
        saveButton = view.findViewById(R.id.saveBtnID);

        dateEditText = view.findViewById(R.id.dateId);
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        requireContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        dateEditText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        // Populate EditText fields with existing data
        if (getArguments() != null) {
            dateEditText.setText(getArguments().getString("date"));
            shopNameEditText.setText(getArguments().getString("shopName"));
            customerNameEditText.setText(getArguments().getString("customerName"));
            customerNumberEditText.setText(getArguments().getString("customerNumber"));
            productNameEditText.setText(getArguments().getString("productName"));
            productQuantityEditText.setText(getArguments().getString("productQuantity"));
            priceEditText.setText(getArguments().getString("price"));
        }

        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("ClientData");

        // Set click listener for save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });

        return view;
    }

    private void saveChanges() {
        // Get edited data from EditText fields
        String date = dateEditText.getText().toString().trim();
        String shopName = shopNameEditText.getText().toString().trim();
        String customerName = customerNameEditText.getText().toString().trim();
        String customerNumber = customerNumberEditText.getText().toString().trim();
        String productName = productNameEditText.getText().toString().trim();
        String productQuantity = productQuantityEditText.getText().toString().trim();
        String price = priceEditText.getText().toString().trim();

        // Create a new DataModel object with the updated values
        DataModel updatedData = new DataModel(date, shopName, customerName, customerNumber, productName, productQuantity, price);

        // Update the data in Firebase with the new values
        if (getArguments() != null) {
            databaseReference.child(getArguments().getString("firebaseKey")).setValue(updatedData)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Data updated successfully
                            dismiss(); // Close the dialog
                            Toast.makeText(getContext(), "Data updated successfully", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Failed to update data
                            Toast.makeText(getContext(), "Failed to update data", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}