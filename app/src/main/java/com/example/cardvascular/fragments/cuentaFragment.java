package com.example.cardvascular.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cardvascular.Musica.Musicactivity;
import com.example.cardvascular.R;
import com.example.cardvascular.Ventanas_cuenta.CuentasUSFragment;
import com.example.cardvascular.Ventanas_cuenta.PoliticasdPrivacidadFragment;
import com.example.cardvascular.Ventanas_cuenta.RateUsDialog;
import com.example.cardvascular.login.InicioLogin;

public class cuentaFragment extends Fragment {

    private CardView cvcuenta,CvPoliticas,CDVcalificanos;
    Button btncerrarsesion;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View View = inflater.inflate(R.layout.fragment_cuenta, container, false);

        CDVcalificanos=View.findViewById(R.id.CDVcalificanos);
        CvPoliticas=View.findViewById(R.id.CvPoliticas);
        cvcuenta=View.findViewById(R.id.CvCuenta);

        btncerrarsesion = View.findViewById(R.id.btnCerrarsesion);
        btncerrarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        CvPoliticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                PoliticasdPrivacidadFragment politicasdPrivacidadFragment= new PoliticasdPrivacidadFragment();
                openFragment(politicasdPrivacidadFragment);
            }
        });
        cvcuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CuentasUSFragment cuentasUSFragment = new CuentasUSFragment();

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.main_contenedor, cuentasUSFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        CDVcalificanos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                RateUsDialog rateUsDialog = new RateUsDialog(requireContext());
                rateUsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
                rateUsDialog.setCancelable(false);
                rateUsDialog.show();
            }
        });

        return View;
    }
    private void logout() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Intent intent = new Intent(requireContext(), InicioLogin.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        requireActivity().finish();
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.main_contenedor, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}