//package com.example.saltindustry.TestingOperation;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//
//import android.os.Bundle;
//import android.view.MenuItem;
//
//import com.example.saltindustry.AddFragment;
//import com.example.saltindustry.HomeFragment;
//import com.example.saltindustry.R;
//import com.example.saltindustry.TestingOperation.Fragments.AddData;
//import com.example.saltindustry.TestingOperation.Fragments.ViewData;
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//
//public class MainActivity2 extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
//
//    BottomNavigationView bottomNavigationView;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
//
//        bottomNavigationView
//                = findViewById(R.id.bottomNavigationView);
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(this);
//        loadFragment(new HomeFragment());
//
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//        Fragment fragment = null;
//
//        if (item.getItemId() == R.id.viewData) {
//            fragment = new ViewData();
//        } else if (item.getItemId() == R.id.addData) {
//            fragment = new AddData();
//        }
//        if (fragment != null) {
//            loadFragment(fragment);
//        }
//        return true;
//    }
//
//    void loadFragment(Fragment fragment) {
//        //to attach fragment
//        getSupportFragmentManager().beginTransaction().replace(R.id.relativelayout, fragment).commit();
//    }
//
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        finishAffinity();
//        finish();
//    }
//}
