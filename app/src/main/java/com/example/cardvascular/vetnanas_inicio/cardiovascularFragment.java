package com.example.cardvascular.vetnanas_inicio;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cardvascular.MainActivity2;
import com.example.cardvascular.R;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class cardiovascularFragment extends Fragment {

    private RadioGroup familiaresGroup, fumarGroup, presionGroup, colesterolGroup, pesoGroup, dietaGroup, ejercicioGroup, diabetesGroup, estresGroup, sintomasGroup;
    private TextView resultadoText;
    private Button calcularBtn;
    ImageButton btnivolver;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View View = inflater.inflate(R.layout.fragment_cardiovascular, container, false);


        familiaresGroup = View.findViewById(R.id.familiaresGroup);
        fumarGroup = View.findViewById(R.id.fumarGroup);
        presionGroup = View.findViewById(R.id.presionGroup);
        colesterolGroup = View.findViewById(R.id.colesterolGroup);
        pesoGroup = View.findViewById(R.id.pesoGroup);
        dietaGroup = View.findViewById(R.id.dietaGroup);
        ejercicioGroup = View.findViewById(R.id.ejercicioGroup);
        diabetesGroup = View.findViewById(R.id.diabetesGroup);
        estresGroup = View.findViewById(R.id.estresGroup);
        sintomasGroup = View.findViewById(R.id.sintomasGroup);

        calcularBtn = View.findViewById(R.id.calcularBtn);
        resultadoText = View.findViewById(R.id.resultadoText);

        calcularBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score = 0;

                if (familiaresGroup.getCheckedRadioButtonId() == R.id.familiaresSi) score += 10;
                if (fumarGroup.getCheckedRadioButtonId() == R.id.fumarSi) score += 10;
                if (presionGroup.getCheckedRadioButtonId() == R.id.presionSi) score += 10;
                if (colesterolGroup.getCheckedRadioButtonId() == R.id.colesterolSi) score += 10;
                if (pesoGroup.getCheckedRadioButtonId() == R.id.pesoSi) score += 10;
                if (dietaGroup.getCheckedRadioButtonId() == R.id.dietaSi) score += 10;
                if (ejercicioGroup.getCheckedRadioButtonId() == R.id.ejercicioNo) score += 10;
                if (diabetesGroup.getCheckedRadioButtonId() == R.id.diabetesSi) score += 10;
                if (estresGroup.getCheckedRadioButtonId() == R.id.estresSi) score += 10;
                if (sintomasGroup.getCheckedRadioButtonId() == R.id.sintomasSi) score += 10;


                int maxScore = 100;
                int riesgo = (score * 100) / maxScore;

                resultadoText.setText("Tu riesgo de enfermedad cardiovascular es del " + riesgo + "%");
                resultadoText.setVisibility(View.VISIBLE);
            }
        });
        btnivolver = View.findViewById(R.id.btniVolver);


        btnivolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity2.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.izquierda1, R.anim.derecha1);
                getActivity().finish();
            }
        });
        return View;
    }



}
