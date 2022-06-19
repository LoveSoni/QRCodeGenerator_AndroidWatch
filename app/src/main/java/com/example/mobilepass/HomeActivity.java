package com.example.mobilepass;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {
    private Button qrCodeButton;
    private Button otpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_home);
        qrCodeButton = (Button) findViewById(R.id.qrCodeButton);
        otpButton = (Button) findViewById(R.id.otpButton);
        qrCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent qrCodeIntent = new Intent(HomeActivity.this, QRCodeActivity.class);
                startActivity(qrCodeIntent);
            }
        });
    }
}