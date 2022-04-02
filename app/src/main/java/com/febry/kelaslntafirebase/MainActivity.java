package com.febry.kelaslntafirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin, btnSignup;
    private EditText etEmail, etPassword;


    //Alt + Enter untuk ngilangin merahnya
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btn_login);
        btnSignup = findViewById(R.id.btn_signup);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);


        mAuth = FirebaseAuth.getInstance();

        //Signup
        btnSignup.setOnClickListener(view -> {
            String email, password;

            email = etEmail.getText().toString();
            password = etPassword.getText().toString();


            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(MainActivity.this, task -> {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Sign Up Berhasil!!!", Toast.LENGTH_LONG).show();

                            user = mAuth.getCurrentUser();

                            Toast.makeText(MainActivity.this, "Halo " + user.getEmail(), Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(MainActivity.this, CreateProfileActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Sign Up Gagal!!! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


        });

        btnLogin.setOnClickListener(view -> {
            String email, password;

            email = etEmail.getText().toString();
            password = etPassword.getText().toString();


            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(MainActivity.this, task -> {
                       if(task.isSuccessful()){
                           Toast.makeText(MainActivity.this, "Login Berhasil!!!", Toast.LENGTH_LONG).show();

                           user = mAuth.getCurrentUser();

                           Toast.makeText(MainActivity.this, "Halo " + user.getEmail(), Toast.LENGTH_LONG).show();

                       } else {
                           Toast.makeText(MainActivity.this, "Login Gagal!!! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                       }
                    });
        });

    }
}