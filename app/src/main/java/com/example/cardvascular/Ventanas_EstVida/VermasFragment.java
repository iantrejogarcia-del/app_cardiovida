package com.example.cardvascular.Ventanas_EstVida;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.cardvascular.MainActivity2;
import com.example.cardvascular.R;
import com.example.cardvascular.Ventanas_EstVida.Recetas.EnsGarbanzosFragment;
import com.example.cardvascular.Ventanas_EstVida.Recetas.EnsPolloFragment;
import com.example.cardvascular.Ventanas_EstVida.Recetas.EnsaguafresasFragment;
import com.example.cardvascular.Ventanas_EstVida.Recetas.MuffinsplatanoFragment;
import com.example.cardvascular.Ventanas_EstVida.Recetas.SopalentejasFragment;
import com.example.cardvascular.Ventanas_EstVida.consejossalud.EnsquinoaFragment;
import com.example.cardvascular.fragments.EstilovidaFragment;


public class VermasFragment extends Fragment {

    private CardView CVDensaguafresas, CVDensgrabanzos, CVDsoplentejas, CDVenspollo, muffinsplatano,CDVensQuinoa;
    ImageButton btnivolver;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_vermas, container, false);

        CVDensaguafresas = view.findViewById(R.id.CVDensaguafresas);
        CDVensQuinoa = view.findViewById(R.id.CDVensQuinoa);
        CVDensgrabanzos = view.findViewById(R.id.CVDensgrabanzos);
        CVDsoplentejas = view.findViewById(R.id.CVDsoplentejas);
        CDVenspollo = view.findViewById(R.id.CDVenspollo);
        muffinsplatano = view.findViewById(R.id.muffinsplatano);

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
        CDVensQuinoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(new EnsquinoaFragment());
            }
        });
        CVDensaguafresas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(new EnsaguafresasFragment());
            }
        });

        CVDensgrabanzos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openFragment(new EnsGarbanzosFragment());
            }
        });

        CVDsoplentejas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(new SopalentejasFragment());
            }
        });

        CDVenspollo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(new EnsPolloFragment());
            }
        });

        muffinsplatano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(new MuffinsplatanoFragment());
            }
        });

        return view;
    }

    private void openFragment(Fragment fragment) {
        if (isAdded() && getParentFragmentManager() != null) {
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.main_contenedor, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}