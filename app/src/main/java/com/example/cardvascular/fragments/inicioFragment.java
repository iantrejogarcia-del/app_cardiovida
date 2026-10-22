package com.example.cardvascular.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.cardvascular.R;
import com.example.cardvascular.vetnanas_inicio.EmocionFragment;
import com.example.cardvascular.vetnanas_inicio.MentalFragment;
import com.example.cardvascular.vetnanas_inicio.SuenoFragment;
import com.example.cardvascular.vetnanas_inicio.ventanas_calorias.CaloriasFragment;
import com.example.cardvascular.vetnanas_inicio.ventanas_peso.PesoFragment;
import com.example.cardvascular.vetnanas_inicio.cardiovascularFragment;

import java.util.ArrayList;

public class inicioFragment extends Fragment {

    private CardView cdvcardvascular, cdvpeso, cdvCal, slider,cdvemotion,cdvSueno,cdvSaludm;
    private ImageSlider imgslider;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        cdvCal = view.findViewById(R.id.cdvCal);
        cdvpeso = view.findViewById(R.id.cdvPeso);
        cdvemotion = view.findViewById(R.id.cdvemotion);
        cdvcardvascular = view.findViewById(R.id.carvascular);
        cdvSueno = view.findViewById(R.id.cdvSueno);
        cdvSaludm = view.findViewById(R.id.cdvSaludm);
        slider = view.findViewById(R.id.slider);
        imgslider = view.findViewById(R.id.iamgeslider);


        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.mipmap.correr, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.mipmap.comidaa, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.mipmap.emocion, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.mipmap.dormir, ScaleTypes.FIT));
        imgslider.setImageList(slideModels, ScaleTypes.FIT);

        cdvcardvascular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardiovascularFragment cardiovascularFragment = new cardiovascularFragment();
                openFragment(cardiovascularFragment);
            }
        });

        cdvpeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PesoFragment pesoFragment = new PesoFragment();
                openFragment(pesoFragment);
            }
        });

        cdvCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CaloriasFragment caloriasFragment = new CaloriasFragment();
                openFragment(caloriasFragment);
            }
        });
        cdvemotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmocionFragment emocionFragment= new EmocionFragment();
                openFragment(emocionFragment);
            }
        });
        cdvSueno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuenoFragment suenoFragment= new SuenoFragment();
                openFragment(suenoFragment);
            }
        });
        cdvSaludm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MentalFragment mentalFragment= new MentalFragment();
                openFragment(mentalFragment);
            }
        });

        return view;
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.main_contenedor, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}