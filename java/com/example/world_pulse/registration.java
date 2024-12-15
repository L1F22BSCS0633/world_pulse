package com.example.world_pulse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.world_pulse.R;

import androidx.appcompat.app.AppCompatActivity;

public class registration extends AppCompatActivity {
    EditText etUsername, etEmail, etPassword, etConfirmPassword;
    Button btnRegister;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvRegister = findViewById(R.id.tvRegister);

        btnRegister.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(registration.this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else {
                if(confirmPassword.equals(password)) {
                    SharedPreferences sharedPreferences = getSharedPreferences("UserAuth", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Username", username);
                    editor.putString("Email", email);
                    editor.putString("Password", password);
                    editor.apply();

                    Toast.makeText(registration.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(registration.this, login.class));
                    finish();
                }
                else
                {
                    Toast.makeText(registration.this, "Password Not Match", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvRegister.setOnClickListener(v -> {
            startActivity(new Intent(registration.this, login.class));
            finish();
        });
    }
}
