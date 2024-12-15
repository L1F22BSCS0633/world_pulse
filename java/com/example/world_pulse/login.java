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

public class login extends AppCompatActivity {
    EditText etLoginEmail, etLoginPassword;
    Button btnLogin;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLoginEmail = findViewById(R.id.etLoginEmail);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        btnLogin = findViewById(R.id.login_btn);
        tvRegister = findViewById(R.id.tvRegister);

        btnLogin.setOnClickListener(v -> {
            String email = etLoginEmail.getText().toString().trim();
            String password = etLoginPassword.getText().toString().trim();
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(login.this, "All fields are required", Toast.LENGTH_SHORT).show();
            }
            else
            {
                SharedPreferences sharedPreferences = getSharedPreferences("UserAuth", MODE_PRIVATE);
                String registeredEmail = sharedPreferences.getString("Email", "");
                String registeredPassword = sharedPreferences.getString("Password", "");

                if (email.equals(registeredEmail) && password.equals(registeredPassword)) {
                    Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(login.this, "Login Failed. Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvRegister.setOnClickListener(v -> {
            startActivity(new Intent(login.this, registration.class));
            finish();
        });
    }
}
