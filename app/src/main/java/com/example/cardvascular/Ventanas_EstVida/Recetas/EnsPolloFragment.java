package com.example.cardvascular.Ventanas_EstVida.Recetas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.cardvascular.MainActivity2;
import com.example.cardvascular.R;


public class EnsPolloFragment extends Fragment {


    ImageButton btnivolver;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_ens_pollo, container, false);

        btnivolver=view.findViewById(R.id.btniVolver);
        btnivolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity2.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.izquierda1, R.anim.derecha1);
                getActivity().finish();
            }
        });
        return view;
    }
}