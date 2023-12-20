package com.example.saltindustry;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;



public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private List<DataModel> dataList;

    private List<DataModel> originalList;

    private DatabaseReference databaseReference;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);


        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("ClientData");

        recyclerView = view.findViewById(R.id.list_view);
        SearchView searchView = view.findViewById(R.id.idSV);

        dataList = new ArrayList<>();
        originalList = new ArrayList<>();


        adapter = new CustomAdapter(requireContext(), dataList);


        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        // Retrieve data from Firebase
        retrieveData();

        // Set up SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the data based on the search query
                filterData(newText);
                return true;
            }
        });

        return view;
    }



    private void retrieveData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataList.clear();
                originalList.clear();  // Clear originalList before updating it

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DataModel data = snapshot.getValue(DataModel.class);
                    // Set the Firebase key
                    data.setFirebaseKey(snapshot.getKey());

                    dataList.add(0, data);  // Add data at the beginning of the list
                    originalList.add(data);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "No Data Available", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void filterData(String query) {
        List<DataModel> filteredList = new ArrayList<>();

        // Ensure that originalList is not null before iterating over it
        if (originalList != null) {
            // Iterate through the original data and add matching items to the filtered list
            for (DataModel data : originalList) {
                if (data.getCustomerName().toLowerCase().contains(query.toLowerCase()) ||
                        data.getCustomerNumber().contains(query)) {
                    filteredList.add(data);
                }
            }

            // Update the adapter with the filtered list
            adapter.filterList(filteredList);
        }
    }


}
