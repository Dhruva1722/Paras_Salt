package com.example.saltindustry;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddFragment extends Fragment {

    private EditText shopNameEditText, customerNameEditText, productNameEditText, customerNumberEditText, priceEditText;
    private Button addBtn;

    // Firebase
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View view =   inflater.inflate(R.layout.fragment_add, container, false);


        databaseReference = FirebaseDatabase.getInstance().getReference("ClientData");

        shopNameEditText = view.findViewById(R.id.shopNameID);
        customerNameEditText = view.findViewById(R.id.customerNameID);
        productNameEditText = view.findViewById(R.id.productNameID);
        customerNumberEditText = view.findViewById(R.id.CustomerNumberID);
        priceEditText = view.findViewById(R.id.priceID);
        addBtn = view.findViewById(R.id.addBtnID);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDataToFirebase();
            }
        });

        return view;

    }


    private void addDataToFirebase() {
        // Get values from EditText fields
        String shopName = shopNameEditText.getText().toString().trim();
        String customerName = customerNameEditText.getText().toString().trim();
        String productName = productNameEditText.getText().toString().trim();
        String customerNumber = customerNumberEditText.getText().toString().trim();
        String price = priceEditText.getText().toString().trim();

        // Check if any field is empty
        if (shopName.isEmpty() || customerName.isEmpty() || productName.isEmpty() || customerNumber.isEmpty() || price.isEmpty()) {
            // Handle the case where any field is empty, show a message or perform necessary actions
            return;
        }

        // Create a unique key for each entry
        String entryKey = databaseReference.push().getKey();

        // Create a data object
        DataModel dataModel = new DataModel(shopName, customerName, productName, customerNumber, price);

        // Add data to Firebase
        databaseReference.child(entryKey).setValue(dataModel);

        // Clear EditText fields after adding data
        shopNameEditText.setText("");
        customerNameEditText.setText("");
        productNameEditText.setText("");
        customerNumberEditText.setText("");
        priceEditText.setText("");
    }

}