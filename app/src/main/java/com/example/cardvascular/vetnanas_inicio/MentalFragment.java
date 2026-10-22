package com.example.cardvascular.vetnanas_inicio;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.cardvascular.MainActivity2;
import com.example.cardvascular.R;


public class MentalFragment extends Fragment {

    private RadioGroup radioGroupPregunta1, radioGroupPregunta2, radioGroupPregunta3, radioGroupPregunta4, radioGroupPregunta5;
    private Button btnCalcular;
    private TextView tvResultado;
    ImageButton btnivolver;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mental, container, false);

        radioGroupPregunta1 = rootView.findViewById(R.id.radio_group_pregunta1);
        radioGroupPregunta2 = rootView.findViewById(R.id.radio_group_pregunta2);
        radioGroupPregunta3 = rootView.findViewById(R.id.radio_group_pregunta3);
        radioGroupPregunta4 = rootView.findViewById(R.id.radio_group_pregunta4);
        radioGroupPregunta5 = rootView.findViewById(R.id.radio_group_pregunta5);
        btnCalcular = rootView.findViewById(R.id.btn_calcular);
        tvResultado = rootView.findViewById(R.id.tv_resultado);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularResultado();
            }
        });
        btnivolver =rootView.findViewById(R.id.btniVolver);


        btnivolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity2.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.izquierda1, R.anim.derecha1);
                getActivity().finish();
            }
        });

        return rootView;
    }

    private void calcularResultado() {
        int respuestaPregunta1 = obtenerRespuestaSeleccionada(radioGroupPregunta1);
        int respuestaPregunta2 = obtenerRespuestaSeleccionada(radioGroupPregunta2);
        int respuestaPregunta3 = obtenerRespuestaSeleccionada(radioGroupPregunta3);
        int respuestaPregunta4 = obtenerRespuestaSeleccionada(radioGroupPregunta4);
        int respuestaPregunta5 = obtenerRespuestaSeleccionada(radioGroupPregunta5);

        int totalRespuestas = 5;
        int respuestasCorrectas = respuestaPregunta1 + respuestaPregunta2 + respuestaPregunta3
                + respuestaPregunta4 + respuestaPregunta5;
        int porcentajeSaludMental = (respuestasCorrectas * 10) / totalRespuestas;

        mostrarResultado(porcentajeSaludMental);
    }

    private int obtenerRespuestaSeleccionada(RadioGroup radioGroup) {
        int idSeleccionado = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = getView().findViewById(idSeleccionado);
        if (radioButton != null) {
            if (radioButton.getId() == R.id.radio_pregunta1_opcion1) {
                return 5;
            } else if (radioButton.getId() == R.id.radio_pregunta1_opcion2) {
                return 4;
            } else if (radioButton.getId() == R.id.radio_pregunta1_opcion3) {
                return 3;
            } else if (radioButton.getId() == R.id.radio_pregunta1_opcion4) {
                return 2;
            } else if (radioButton.getId() == R.id.radio_pregunta1_opcion5) {
                return 1;
            } else if (radioButton.getId() == R.id.radio_pregunta2_opcion1) {
                return 5;
            } else if (radioButton.getId() == R.id.radio_pregunta2_opcion2) {
                return 4;
            } else if (radioButton.getId() == R.id.radio_pregunta2_opcion3) {
                return 3;
            } else if (radioButton.getId() == R.id.radio_pregunta2_opcion4) {
                return 2;
            } else if (radioButton.getId() == R.id.radio_pregunta2_opcion5) {
                return 1;
            } else if (radioButton.getId() == R.id.radio_pregunta3_opcion1) {
                return 5;
            } else if (radioButton.getId() == R.id.radio_pregunta3_opcion2) {
                return 4;
            } else if (radioButton.getId() == R.id.radio_pregunta3_opcion3) {
                return 3;
            } else if (radioButton.getId() == R.id.radio_pregunta3_opcion4) {
                return 2;
            } else if (radioButton.getId() == R.id.radio_pregunta3_opcion5) {
                return 1;
            } else if (radioButton.getId() == R.id.radio_pregunta4_opcion1) {
                return 5;
            } else if (radioButton.getId() == R.id.radio_pregunta4_opcion2) {
                return 4;
            } else if (radioButton.getId() == R.id.radio_pregunta4_opcion3) {
                return 3;
            } else if (radioButton.getId() == R.id.radio_pregunta4_opcion4) {
                return 2;
            } else if (radioButton.getId() == R.id.radio_pregunta4_opcion5) {
                return 1;
            } else if (radioButton.getId() == R.id.radio_pregunta5_opcion1) {
                return 5;
            } else if (radioButton.getId() == R.id.radio_pregunta5_opcion2) {
                return 4;
            } else if (radioButton.getId() == R.id.radio_pregunta5_opcion3) {
                return 3;
            } else if (radioButton.getId() == R.id.radio_pregunta5_opcion4) {
                return 2;
            } else if (radioButton.getId() == R.id.radio_pregunta5_opcion5) {
                return 1;
            }
        }
        return 0;
    }

    private void mostrarResultado(int porcentajeSaludMental) {
        tvResultado.setText("Tu salud mental está al " + porcentajeSaludMental + "%.");


        String sugerencias = getSugerencias(porcentajeSaludMental);
        tvResultado.append("\n\n" + sugerencias);
    }

    private String getSugerencias(int porcentaje) {
        if (porcentaje >= 50) {
            return "¡Excelente! Sigue cuidando tu salud mental.";
        } else if (porcentaje <= 40) {
            return "Tu salud mental está bien, pero podrías considerar algunas prácticas de bienestar mental.";
        } else if (porcentaje <= 30) {
            return "Parece que podrías estar experimentando algunos desafíos. Considera buscar apoyo.";
        } else {
            return "Es posible que estés experimentando altos niveles de estrés o ansiedad. Busca ayuda profesional si es necesario.";
        }
    }
}
