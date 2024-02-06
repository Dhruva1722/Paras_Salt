//package com.example.saltindustry.TestingOperation;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//
//import com.example.saltindustry.MainActivity;
//import com.example.saltindustry.R;
//
//public class MainSplashScreen extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_splash_screen);
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(getApplicationContext(), LoginScreen.class));
//            }
//        },2000);
//    }
//}