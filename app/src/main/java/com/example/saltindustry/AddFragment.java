package com.example.saltindustry;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;


public class AddFragment extends Fragment {

    private TextInputEditText shopNameEditText, customerNameEditText, productNameEditText, customerNumberEditText, priceEditText , productQuantityEditText ;
    private Button addBtn;

    private EditText dateEdt;
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
        productQuantityEditText = view.findViewById(R.id.productQuantityID);
        dateEdt = view.findViewById(R.id.dateId);
        dateEdt.setOnClickListener(new View.OnClickListener() {
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
                                dateEdt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDataToFirebase();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.relativelayout, new HomeFragment())
                        .commit();
            }
        });
        return view;

    }
    private void addDataToFirebase() {
        String date = dateEdt.getText().toString().trim();
        String shopName = shopNameEditText.getText().toString().trim();
        String customerName = customerNameEditText.getText().toString().trim();
        String productName = productNameEditText.getText().toString().trim();
        String productQuantity = productQuantityEditText.getText().toString().trim();
        String customerNumber = customerNumberEditText.getText().toString().trim();
        String price = priceEditText.getText().toString().trim();

        if ( date.isEmpty() ||  shopName.isEmpty() || customerName.isEmpty() || productName.isEmpty() || productQuantity.isEmpty() || customerNumber.isEmpty() || price.isEmpty()) {
            Toast.makeText(getContext(), "All fields Require", Toast.LENGTH_LONG).show();
            return;
        }

        String entryKey = databaseReference.push().getKey();

        DataModel dataModel = new DataModel(date ,shopName, customerName, productName ,productQuantity , customerNumber, price);

        databaseReference.child(entryKey).setValue(dataModel);

        dateEdt.setText("");
        shopNameEditText.setText("");
        customerNameEditText.setText("");
        productNameEditText.setText("");
        productQuantityEditText.setText("");
        customerNumberEditText.setText("");
        priceEditText.setText("");
        Toast.makeText(getContext(), "Data save Successfully", Toast.LENGTH_LONG).show();

    }

}