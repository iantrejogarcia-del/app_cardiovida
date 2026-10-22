package com.example.cardvascular.vetnanas_inicio;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardvascular.MainActivity2;
import com.example.cardvascular.R;


public class EmocionFragment extends Fragment {

    private EditText etDetails;
    private TextView tvRecommendation;
    private String currentMood;
    ImageButton btnHappy,btnSad,btnAngry,btnivolver;
    Button enviar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_emocion, container, false);
         etDetails = view.findViewById(R.id.et_details);
         tvRecommendation = view.findViewById(R.id.tv_recommendation);
         btnHappy = view.findViewById(R.id.btn_happy);
         btnSad = view.findViewById(R.id.btn_sad);
         btnAngry = view.findViewById(R.id.btn_angry);
         enviar = view.findViewById(R.id.btn_submit);

        btnHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentMood = "happy";
                etDetails.setHint("¿Por qué te sientes feliz?");
            }
        });

        btnSad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentMood = "sad";
                etDetails.setHint("¿Por qué te sientes triste?");
            }
        });

        btnAngry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentMood = "angry";
                etDetails.setHint("¿Por qué te sientes enojado?");
            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String details = etDetails.getText().toString();
                if (TextUtils.isEmpty(currentMood)) {
                    Toast.makeText(getActivity(), "Por favor, selecciona tu estado de ánimo.", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(details)) {
                    Toast.makeText(getActivity(), "Por favor, escribe más detalles.", Toast.LENGTH_SHORT).show();
                } else {
                    showRecommendation(currentMood, details);
                }
            }
        });
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

    private void showRecommendation(String mood, String details) {
        String recommendation = "";

        switch (mood) {
            case "happy":
                recommendation = "¡Qué bien que te sientas feliz! Sigue haciendo lo que te hace feliz y comparte tu alegría con los demás. Practicar gratitud y pasar tiempo con tus seres queridos puede prolongar ese sentimiento de felicidad.";
                break;
            case "sad":
                recommendation = "Lamentamos que te sientas triste. Hablar con alguien de confianza sobre lo que te molesta, puede ayudar. Intenta hacer algo que disfrutes, como escuchar música o dar un paseo. Recuerda que está bien sentirse triste a veces.";
                break;
            case "angry":
                recommendation = "Es normal sentirse enojado. Intenta respirar profundamente y contar hasta diez. Hablar sobre lo que te molesta con alguien de confianza puede ser útil. La actividad física también puede ayudar a liberar la tensión.";
                break;
        }

        tvRecommendation.setText(recommendation);
        tvRecommendation.setVisibility(View.VISIBLE);
    }
}