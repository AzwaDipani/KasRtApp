package com.example.kasrtapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MenuAcitivity extends AppCompatActivity {

    LinearLayout tombolSatu;
    LinearLayout tombolDua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_acitivity);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        tombolSatu = findViewById(R.id.cdMenu1);
        tombolSatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuAcitivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        tombolDua = findViewById(R.id.cdMenu2);
        tombolDua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuAcitivity.this, LaporanActivity.class);
                startActivity(intent);
            }
        });


    }
}