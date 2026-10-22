package com.example.cardvascular.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.cardvascular.Musica.Musicactivity;
import com.example.cardvascular.R;
import com.example.cardvascular.Ventanas_EstVida.Recetas.EnsGarbanzosFragment;
import com.example.cardvascular.Ventanas_EstVida.Recetas.EnsPolloFragment;
import com.example.cardvascular.Ventanas_EstVida.Recetas.EnsaguafresasFragment;
import com.example.cardvascular.Ventanas_EstVida.Recetas.MuffinsplatanoFragment;
import com.example.cardvascular.Ventanas_EstVida.Recetas.SopalentejasFragment;
import com.example.cardvascular.Ventanas_EstVida.VermasFragment;
import com.example.cardvascular.Ventanas_EstVida.consejossalud.ComidasaludableFragment;
import com.example.cardvascular.Ventanas_EstVida.consejossalud.CtupesoFragment;
import com.example.cardvascular.Ventanas_EstVida.consejossalud.EjerciciosFragment;
import com.example.cardvascular.Ventanas_EstVida.consejossalud.ParterialFragment;
import com.example.cardvascular.Ventanas_EstVida.consejossalud.AguaFragment;

public class EstilovidaFragment extends Fragment {

    private CardView CVDensaguafresas, CVDensgrabanzos, CVDsoplentejas, CDVenspollo, muffinsplatano;
    private CardView CDVparterial,CDVpeso,CDVejercicio,CDVdieta,CDVagua;
    private Button btnvermas;
    ImageButton btnMusic;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_estilovida, container, false);

        CVDensaguafresas = view.findViewById(R.id.CVDensaguafresas);
        CVDensgrabanzos = view.findViewById(R.id.CVDensgrabanzos);
        CVDsoplentejas = view.findViewById(R.id.CVDsoplentejas);
        CDVenspollo = view.findViewById(R.id.CDVenspollo);
        muffinsplatano = view.findViewById(R.id.muffinsplatano);
        CDVparterial = view.findViewById(R.id.CDVparterial);
        CDVpeso = view.findViewById(R.id.CDVpeso);
        CDVejercicio = view.findViewById(R.id.CDVejercicio);
        CDVdieta = view.findViewById(R.id.CDVdieta);
        CDVagua = view.findViewById(R.id.CDVagua);
        btnvermas = view.findViewById(R.id.btnvermas);
        btnMusic=view.findViewById(R.id.btnMusic);

        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Musicactivity.class);
                startActivity(intent);
            }
        });
        btnvermas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VermasFragment vermasFragment = new VermasFragment();
                openFragment(vermasFragment);
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
            public void onClick(View v) {
                openFragment(new EnsGarbanzosFragment());
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

        CDVparterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(new ParterialFragment());
            }
        });
        CDVpeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openFragment(new CtupesoFragment());
            }
        });
        CDVejercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(new EjerciciosFragment());
            }
        });
        CDVdieta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(new ComidasaludableFragment());
            }
        });
        CDVagua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(new AguaFragment());
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