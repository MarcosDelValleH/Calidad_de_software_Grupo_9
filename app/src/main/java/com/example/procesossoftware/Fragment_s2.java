package com.example.procesossoftware;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Fragment_s2 extends Fragment {

    PreferencesManager prefManager;
    statisticsManager  graficManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view_general = inflater.inflate(R.layout.fragment_s2, container, false);
        View view = inflater.inflate(R.layout.grafico_cigarros, (ViewGroup) view_general);
        prefManager = new PreferencesManager(requireContext());
        Registro r = prefManager.getReg();
        graficManager = new statisticsManager(view_general,view, r);
        graficManager.setAhorro();
        graficManager.setGrafica();

        return view_general;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Actualiza la gráfica cada vez que el fragmento se vuelve visible
        Registro r = prefManager.getReg();
        graficManager.setRegistro(r);
        graficManager.setGrafica();  // Le pasa la view de fragment2.xml, pero funciona igual
        graficManager.setAhorro();
    }

}