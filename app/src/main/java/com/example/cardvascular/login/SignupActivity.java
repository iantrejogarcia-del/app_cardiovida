package com.example.cardvascular.login;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cardvascular.MainActivity2;
import com.example.cardvascular.Onboarding.OnboardingActivity;
import com.example.cardvascular.R;
import com.example.cardvascular.login.bd.DatabaseHelper;

public class SignupActivity extends AppCompatActivity {
    EditText editTextUsername, editTextEmail, editTextPassword, editTextConfirmPassword;
    DatabaseHelper dbHelper;
    ImageButton btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnback = findViewById(R.id.back);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, InicioLogin.class);
                startActivity(intent);
                overridePendingTransition(R.anim.izquierda1, R.anim.derecha1);
                finish();
            }
        });

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);

        dbHelper = new DatabaseHelper(this);
    }

    public void signup(View view) {
        String username = editTextUsername.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USERNAME, username);
        values.put(DatabaseHelper.COLUMN_EMAIL, email);
        values.put(DatabaseHelper.COLUMN_PASSWORD, password);

        long newRowId = database.insert(DatabaseHelper.TABLE_USERS, null, values);
        if (newRowId != -1) {
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            database.close();

            SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("logged_in", true);
            editor.putString("username", username);
            editor.putString("email", email);
            editor.apply();

            Intent intent = new Intent(this, OnboardingActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
            database.close();
        }
    }
}