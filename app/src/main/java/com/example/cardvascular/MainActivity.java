package com.example.cardvascular;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cardvascular.login.InicioLogin;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);

        int delayMillis = 3000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sharedPreferences.getBoolean("logged_in", false)) {
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(MainActivity.this, InicioLogin.class);
                    startActivity(intent);
                    finish();
                }
                overridePendingTransition(R.anim.entrada, R.anim.salida);
                finish();
            }
        }, delayMillis);
    }
}