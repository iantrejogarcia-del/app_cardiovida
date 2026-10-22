package com.example.cardvascular.vetnanas_inicio.ventanas_calorias;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import com.example.cardvascular.MainActivity2;
import com.example.cardvascular.R;
import com.example.cardvascular.login.bd.DatabaseHelper;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class CaloriasFragment extends Fragment {

    private Spinner spinnerFood;
    private Button buttonAdd;
    private BarChart barChart;
    private ArrayList<BarEntry> barEntries;
    private ArrayList<String> labels;
    private int mealCount;

    private SQLiteDatabase userDatabase;
    private SQLiteDatabase caloriePerDayDatabase;
    private DatabaseHelper dbHelper;
    private CaloriePerDayDatabaseHelper caloriePerDayDbHelper;
    private String username;

    private EditText editTextDate;
    private ListView listViewCalories;
    private ArrayAdapter<String> listAdapter;
    private ArrayList<String> caloriesList;

    private HashMap<String, Float> foodCalories = new HashMap<>();

    private TextView textViewTotalCalories;

    public CaloriasFragment() {
        foodCalories.put("Manzana", 52f);
        foodCalories.put("Banana", 89f);
        foodCalories.put("Pollo", 239f);
        foodCalories.put("Pasta", 131f);
        foodCalories.put("Ensalada", 118f);
    }
    ImageButton btnivolver;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calorias, container, false);

        btnivolver = view.findViewById(R.id.btniVolver);
        btnivolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity2.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.izquierda1, R.anim.derecha1);
                getActivity().finish();
            }
        });
        spinnerFood = view.findViewById(R.id.spinnerFood);
        buttonAdd = view.findViewById(R.id.buttonAdd);
        barChart = view.findViewById(R.id.barChart);
        editTextDate = view.findViewById(R.id.editTextDate);
        listViewCalories = view.findViewById(R.id.listViewCalories);
        textViewTotalCalories = view.findViewById(R.id.textViewTotalCalories);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, new ArrayList<>(foodCalories.keySet()));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFood.setAdapter(adapter);
        barEntries = new ArrayList<>();
        labels = new ArrayList<>();
        mealCount = 0;
        caloriesList = new ArrayList<>();
        listAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, caloriesList);
        listViewCalories.setAdapter(listAdapter);
        dbHelper = new DatabaseHelper(requireContext());
        userDatabase = dbHelper.getWritableDatabase();
        caloriePerDayDbHelper = new CaloriePerDayDatabaseHelper(requireContext());
        caloriePerDayDatabase = caloriePerDayDbHelper.getWritableDatabase();
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFood();
            }
        });
        setupBarChart();
        loadTotalCalories();
        return view;
    }
    private void setupBarChart() {
        loadCaloriesData();

        BarDataSet barDataSet = new BarDataSet(barEntries, "Calorías por Comida");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        barChart.invalidate();
    }

    private void loadCaloriesData() {
        String[] columns = {CaloriePerDayDatabaseHelper.COLUMN_FOOD_NAME, CaloriePerDayDatabaseHelper.COLUMN_TOTAL_CALORIES, CaloriePerDayDatabaseHelper.COLUMN_DATE};
        String selection = CaloriePerDayDatabaseHelper.COLUMN_USERNAME + "=?";
        String[] selectionArgs = {username};
        Cursor cursor = caloriePerDayDatabase.query(CaloriePerDayDatabaseHelper.TABLE_CALORIES_PER_DAY, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String foodName = cursor.getString(cursor.getColumnIndex(CaloriePerDayDatabaseHelper.COLUMN_FOOD_NAME));
                @SuppressLint("Range") float calories = cursor.getFloat(cursor.getColumnIndex(CaloriePerDayDatabaseHelper.COLUMN_TOTAL_CALORIES));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex(CaloriePerDayDatabaseHelper.COLUMN_DATE));
                barEntries.add(new BarEntry(mealCount, calories));
                labels.add(foodName);
                caloriesList.add(date + ": " + foodName + " - " + calories + " cal");
                mealCount++;
            } while (cursor.moveToNext());

            listAdapter.notifyDataSetChanged();
            cursor.close();
        }
    }

    private void addFood() {
        try {
            String foodName = spinnerFood.getSelectedItem().toString();
            String date = editTextDate.getText().toString();

            if (date.isEmpty()) {
                Toast.makeText(requireContext(), "Por favor ingrese una fecha", Toast.LENGTH_SHORT).show();
                return;
            }

            Float calories = foodCalories.get(foodName);
            if (calories == null) {
                Toast.makeText(requireContext(), "Comida no válida", Toast.LENGTH_SHORT).show();
                return;
            }

            long newRowId = caloriePerDayDbHelper.insertCalories(username, date, foodName, calories);

            if (newRowId != -1) {
                Toast.makeText(requireContext(), "Comida añadida con éxito", Toast.LENGTH_SHORT).show();
                barEntries.add(new BarEntry(mealCount, calories));
                labels.add(foodName);
                caloriesList.add(date + ": " + foodName + " - " + calories + " cal");

                BarDataSet barDataSet = new BarDataSet(barEntries, "Calorías por Comida");
                BarData barData = new BarData(barDataSet);
                barChart.setData(barData);

                XAxis xAxis = barChart.getXAxis();
                xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
                xAxis.setGranularity(1f);
                xAxis.setGranularityEnabled(true);

                barChart.invalidate();
                listAdapter.notifyDataSetChanged();
                spinnerFood.setSelection(0);
                editTextDate.setText("");
                updateTotalCalories();
            } else {
                Toast.makeText(requireContext(), "Error al añadir comida", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(requireContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("Range")
    private void loadTotalCalories() {
        String currentDate = getCurrentDate();

        String[] columns = {CaloriePerDayDatabaseHelper.COLUMN_TOTAL_CALORIES};
        String selection = CaloriePerDayDatabaseHelper.COLUMN_USERNAME + "=? AND " + CaloriePerDayDatabaseHelper.COLUMN_DATE + "=?";
        String[] selectionArgs = {username, currentDate};
        Cursor cursor = caloriePerDayDatabase.query(CaloriePerDayDatabaseHelper.TABLE_CALORIES_PER_DAY, columns, selection, selectionArgs, null, null, null);

        float totalCalories = 0;

        if (cursor != null && cursor.moveToFirst()) {
            do {
                totalCalories += cursor.getFloat(cursor.getColumnIndex(CaloriePerDayDatabaseHelper.COLUMN_TOTAL_CALORIES));
            } while (cursor.moveToNext());

            cursor.close();
        }

        textViewTotalCalories.setText(String.format(Locale.getDefault(), "Total Calorías: %.2f", totalCalories));
    }

    @SuppressLint("Range")
    private void updateTotalCalories() {
        String currentDate = getCurrentDate();

        String[] columns = {CaloriePerDayDatabaseHelper.COLUMN_TOTAL_CALORIES};
        String selection = CaloriePerDayDatabaseHelper.COLUMN_USERNAME + "=? AND " + CaloriePerDayDatabaseHelper.COLUMN_DATE + "=?";
        String[] selectionArgs = {username, currentDate};
        Cursor cursor = caloriePerDayDatabase.query(CaloriePerDayDatabaseHelper.TABLE_CALORIES_PER_DAY, columns, selection, selectionArgs, null, null, null);

        float totalCalories = 0;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                totalCalories += cursor.getFloat(cursor.getColumnIndex(CaloriePerDayDatabaseHelper.COLUMN_TOTAL_CALORIES));
            } while (cursor.moveToNext());

            cursor.close();
        }

        textViewTotalCalories.setText(String.format(Locale.getDefault(), "Total Calorías: %.2f", totalCalories));
    }

    private String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}