package com.example.cardvascular;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.cardvascular.fragments.EstilovidaFragment;
import com.example.cardvascular.fragments.cuentaFragment;
import com.example.cardvascular.fragments.inicioFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;

public class MainActivity2 extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_contenedor, new inicioFragment())
                    .commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    if (item.getItemId() == R.id.navhome) {
                        selectedFragment = new inicioFragment();
                    } else if (item.getItemId() == R.id.navcuenta) {
                        selectedFragment = new cuentaFragment();
                    } else if (item.getItemId() == R.id.nav_stilo) {
                        selectedFragment = new EstilovidaFragment();
                    }

                    if (selectedFragment != null) {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_contenedor, selectedFragment)
                                .addToBackStack(null)
                                .commit();
                        return true;
                    }

                    return false;
                }
            };
}