package com.example.cardvascular.vetnanas_inicio;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.cardvascular.MainActivity2;
import com.example.cardvascular.R;


public class SuenoFragment extends Fragment {

    private EditText editTextBedTime;
    private EditText editTextWakeUpTime;
    private EditText editTextSleepHours;
    private RadioGroup radioGroupRested;
    private TextView textViewSleepAdvice;
    ImageButton btnivolver;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sueno, container, false);

        editTextBedTime = view.findViewById(R.id.editTextBedTime);
        editTextWakeUpTime = view.findViewById(R.id.editTextWakeUpTime);
        editTextSleepHours = view.findViewById(R.id.editTextSleepHours);
        radioGroupRested = view.findViewById(R.id.radioGroupRested);
        textViewSleepAdvice = view.findViewById(R.id.textViewSleepAdvice);

        Button buttonSubmit = view.findViewById(R.id.buttonSubmitSleep);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                provideSleepAdvice();
            }
        });
        btnivolver =view.findViewById(R.id.btniVolver);


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

    private void provideSleepAdvice() {
        String bedTime = editTextBedTime.getText().toString();
        String wakeUpTime = editTextWakeUpTime.getText().toString();
        String sleepHoursStr = editTextSleepHours.getText().toString();
        int selectedRested = radioGroupRested.getCheckedRadioButtonId();

        if (bedTime.isEmpty() || wakeUpTime.isEmpty() || sleepHoursStr.isEmpty() || selectedRested == -1) {
            textViewSleepAdvice.setText("Por favor, responde todas las preguntas.");
            return;
        }

        int sleepHours = Integer.parseInt(sleepHoursStr);
        boolean feelsRested = selectedRested == R.id.radioYes;

        StringBuilder advice = new StringBuilder("Consejos para mejorar tu sueño:\n");
        if (sleepHours < 7 || !feelsRested) {
            advice.append("- Intenta mantener un horario de sueño consistente.\n");
            advice.append("- Crea un ambiente propicio para el sueño: oscuro, tranquilo y cómodo.\n");
            advice.append("- Evita el uso de dispositivos electrónicos antes de dormir.\n");
            advice.append("- Realiza actividades relajantes antes de acostarte.\n");
        } else {
            advice.append("- Sigue manteniendo tus buenos hábitos de sueño.\n");
        }

        textViewSleepAdvice.setText(advice.toString());
    }
}