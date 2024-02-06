//package com.example.saltindustry.TestingOperation;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.saltindustry.MainActivity;
//import com.example.saltindustry.R;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.android.material.textfield.TextInputEditText;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//
//public class LoginScreen extends AppCompatActivity {
//
//    private TextInputEditText emailTextView, passwordTextView;
//    private Button Btn;
//    private ProgressBar progressbar;
//
//    TextView newuser;
//    private FirebaseAuth mAuth;
//    private SharedPreferences sharedPreferences;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login_screen);
//
//        mAuth = FirebaseAuth.getInstance();
//
//        sharedPreferences = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
//        if (sharedPreferences.getBoolean("loggedIn", false)) {
//            startActivity(new Intent(LoginScreen.this, MainActivity2.class));
//            finish();
//        }
//
//        emailTextView = findViewById(R.id.loginEmailID);
//        passwordTextView = findViewById(R.id.loginPasswordID);
//        Btn = findViewById(R.id.loginBtnID);
//        progressbar = findViewById(R.id.progressbar);
//        newuser = findViewById(R.id.newUserID);
//
//       newuser.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//               Intent intent
//                       = new Intent(LoginScreen.this,
//                       RegistrationScreen.class);
//               startActivity(intent);
//           }
//       });
//        Btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                loginUserAccount();
//            }
//        });
//    }
//
//    private void loginUserAccount()
//    {
//        // show the visibility of progress bar to show loading
//        progressbar.setVisibility(View.VISIBLE);
//
//        // Take the value of two edit texts in Strings
//        String email, password;
//        email = emailTextView.getText().toString();
//        password = passwordTextView.getText().toString();
//
//        // validations for input email and password
//        if (TextUtils.isEmpty(email)) {
//            Toast.makeText(getApplicationContext(),
//                            "Please enter email!!",
//                            Toast.LENGTH_LONG)
//                    .show();
//            return;
//        }
//
//        if (TextUtils.isEmpty(password)) {
//            Toast.makeText(getApplicationContext(),
//                            "Please enter password!!",
//                            Toast.LENGTH_LONG)
//                    .show();
//            return;
//        }
//
//        // signin existing user
//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(
//                        new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(
//                                    @NonNull Task<AuthResult> task)
//                            {
//                                if (task.isSuccessful()) {
//                                    Toast.makeText(getApplicationContext(),
//                                                    "Login successful!!",
//                                                    Toast.LENGTH_LONG)
//                                            .show();
//
//                                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                                    editor.putBoolean("loggedIn", true);
//                                    editor.apply();
//
//                                    progressbar.setVisibility(View.GONE);
//                                    // intent to home activity
//                                    Intent intent
//                                            = new Intent(LoginScreen.this,
//                                            MainActivity2.class);
//                                    startActivity(intent);
//                                }
//
//                                else {
//
//                                    // sign-in failed
//                                    Toast.makeText(getApplicationContext(),
//                                                    "Login failed!!",
//                                                    Toast.LENGTH_LONG)
//                                            .show();
//
//                                    // hide the progress bar
//                                    progressbar.setVisibility(View.GONE);
//                                }
//                            }
//                        });
//    }
//}
