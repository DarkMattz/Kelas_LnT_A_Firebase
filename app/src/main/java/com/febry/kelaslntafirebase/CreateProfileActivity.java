package com.febry.kelaslntafirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class CreateProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;


    private Button btnOk;
    private EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        btnOk = findViewById(R.id.btn_ok);
        etName = findViewById(R.id.et_name);


        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser(); //diasumsikan udh ada yang login


        btnOk.setOnClickListener(view -> {
            String name;

            name = etName.getText().toString();


            UserProfileChangeRequest newProfile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build();

            user.updateProfile(newProfile)
                .addOnCompleteListener(CreateProfileActivity.this, task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(CreateProfileActivity.this, "Update Profile Berhasil", Toast.LENGTH_SHORT).show();

                        Toast.makeText(CreateProfileActivity.this, "Halo " + user.getDisplayName(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(CreateProfileActivity.this, "Update Profile Gagal " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        });

    }
}