package com.example.cardvascular.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cardvascular.MainActivity2;
import com.example.cardvascular.R;
import com.example.cardvascular.login.bd.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {
    EditText editTextUsername, editTextPassword;
    DatabaseHelper dbHelper;
    ImageButton btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnback = findViewById(R.id.back);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, InicioLogin.class);
                startActivity(intent);
                overridePendingTransition(R.anim.izquierda1, R.anim.derecha1);
                finish();
            }
        });

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);

        dbHelper = new DatabaseHelper(this);
    }

    public void login(View view) {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String[] columns = {DatabaseHelper.COLUMN_ID};
        String selection = DatabaseHelper.COLUMN_USERNAME + "=? AND " + DatabaseHelper.COLUMN_PASSWORD + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = database.query(DatabaseHelper.TABLE_USERS, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            cursor.close();
            database.close();

            SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("logged_in", true);
            editor.putString("username", username);
            editor.apply();

            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Nombre de usuario o Contrasena incorrectos", Toast.LENGTH_SHORT).show();
            if (cursor != null) {
                cursor.close();
            }
            database.close();
        }
    }

    public void goToSignup(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
        finish();
    }
}