package com.example.cardvascular.vetnanas_inicio.ventanas_peso;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cardvascular.MainActivity2;
import com.example.cardvascular.R;

public class PesoFragment extends Fragment {

    private EditText etHeight;
    private EditText etAge;
    private EditText etWeight;
    private Button btnCalculate;
    private TextView tvResult;
    private ImageButton btnivolver;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_peso, container, false);

        etHeight = view.findViewById(R.id.etHeight);
        etAge = view.findViewById(R.id.etAge);
        etWeight = view.findViewById(R.id.etWeight);
        btnCalculate = view.findViewById(R.id.btnCalculate);
        tvResult = view.findViewById(R.id.tvResult);
        btnivolver = view.findViewById(R.id.btniVolver);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String heightStr = etHeight.getText().toString();
                String ageStr = etAge.getText().toString();
                String weightStr = etWeight.getText().toString();

                if (!heightStr.isEmpty() && !ageStr.isEmpty() && !weightStr.isEmpty()) {
                    double height = Double.parseDouble(heightStr);
                    int age = Integer.parseInt(ageStr);
                    double weight = Double.parseDouble(weightStr);

                    double idealWeight = calculateIdealWeight(height, age);
                    String result = evaluateWeight(weight, idealWeight);

                    tvResult.setText(result);
                    tvResult.setVisibility(View.VISIBLE);
                } else {
                    tvResult.setText("Por favor, ingrese todos los datos correctamente.");
                    tvResult.setVisibility(View.VISIBLE);
                }
            }
        });

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

    private double calculateIdealWeight(double height, int age) {
        return 50 + 0.75 * (height - 150) + (age - 20) / 4.0;
    }

    private String evaluateWeight(double currentWeight, double idealWeight) {
        if (currentWeight < idealWeight) {
            return "Estás por debajo de tu peso ideal. Considera aumentar tu ingesta calórica.";
        } else if (currentWeight > idealWeight) {
            return "Estás por encima de tu peso ideal. Considera reducir tu ingesta calórica y hacer más ejercicio.";
        } else {
            return "Estás en tu peso ideal. ¡Sigue manteniendo un estilo de vida saludable!";
        }
    }
}