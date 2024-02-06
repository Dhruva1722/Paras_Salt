//package com.example.saltindustry.TestingOperation.Fragments;
//
//import android.content.Intent;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentTransaction;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.saltindustry.CustomAdapter;
//import com.example.saltindustry.DataModel;
//import com.example.saltindustry.R;
//import com.example.saltindustry.TestingOperation.Adapter.CustomerAdapter;
//import com.example.saltindustry.TestingOperation.MainActivity2;
//import com.example.saltindustry.TestingOperation.RegistrationScreen;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class ViewData extends Fragment {
//
//    private RecyclerView recyclerView;
//    private CustomerAdapter adapter;
//    private List<DataModel> dataList;
//    private DatabaseReference databaseReference;
//    private FirebaseAuth mAuth;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_view_data, container, false);
//        recyclerView = view.findViewById(R.id.list_view);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        dataList = new ArrayList<>();
//        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        databaseReference = FirebaseDatabase.getInstance().getReference();
//
//        // Check if user is authenticated
//        if (currentUser != null) {
//            String userId = currentUser.getUid();
//            Query query = databaseReference.child("Users").child(userId).child("customerData");
//
//            query.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    dataList.clear();
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                        DataModel data = snapshot.getValue(DataModel.class);
//                        dataList.add(data);
//                    }
//                    adapter.notifyDataSetChanged();
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//                    // Handle error
//                }
//            });
//        }
//
//        adapter = new CustomerAdapter(getContext(), dataList);
//        recyclerView.setAdapter(adapter);
//
//        // Set delete and edit click listeners
//        adapter.setOnDeleteClickListener(new CustomerAdapter.OnDeleteClickListener() {
//            @Override
//            public void onDeleteClick(DataModel data) {
//                deleteData(data);
//            }
//        });
//
//        adapter.setOnEditClickListener(new CustomerAdapter.OnEditClickListener() {
//            @Override
//            public void onEditClick(DataModel data) {
////                // Replace the current fragment with the EditData fragment
////                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
////                transaction.replace(R.id.fragment_container, new EditData());
////                transaction.addToBackStack(null); // Optional: Add to back stack to allow back navigation
////                transaction.commit();
//                Intent intent
//                        = new Intent(requireContext(),
//                        EditData.class);
//                startActivity(intent);
//
//            }
//        });
//
//        return view;
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        // Refresh data when fragment is resumed
////        fetchDataFromFirebase();
//    }
//
//    private void deleteData(DataModel data) {
//        DatabaseReference dbRef = databaseReference.child("Users").child(mAuth.getCurrentUser().getUid()).child("customerData");
//        dbRef.child(data.getFirebaseKey()).removeValue();
//    }
//
//
//}
