package com.example.procesossoftware;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Fragment_s3 extends Fragment {
    private TextView textViewFragment;
    private AlertDialog alertDialog;
    private ArrayList<String> advices;
    private Button buttonChangeAdvice;
    private Button buttonAddAdvice;

    private Button buttonViewObjectives;

    private PrevisionesManager objectives;
    private Set<Integer> shownAdvices = new HashSet<>(); // Conjunto de índices de consejos mostrados
    private ConsejosManager consejosManager;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_s3, container, false);

        // Obtener referencia al TextView del fragmento
        textViewFragment = view.findViewById(R.id.textViewFragment);
        buttonChangeAdvice = view.findViewById(R.id.buttonC);
        buttonAddAdvice = view.findViewById(R.id.buttonAdd);
        buttonViewObjectives = view.findViewById(R.id.buttonObj);
        consejosManager = new ConsejosManager(getContext(), this.getActivity());
        PreferencesManager preferencesManager = new PreferencesManager(getContext());
        objectives = new PrevisionesManager(preferencesManager.getReg());

        // Cargar los consejos desde el archivo de texto en assets
        advices = (ArrayList<String>) consejosManager.getAdvices();

        // Mostrar un consejo al iniciar
        showNextAdvice();

        // Configurar un listener para el botón de cambio de consejo
        buttonChangeAdvice.setOnClickListener(v -> showNextAdvice());
        buttonAddAdvice.setOnClickListener(v -> addAdvice());
        buttonViewObjectives.setOnClickListener(v -> showObjectives());

        return view;
    }


    // Método para cargar los consejos desde el archivo de texto en assets


    // Método para mostrar el próximo consejo
    private void showNextAdvice() {
        if (advices != null && !advices.isEmpty()) {
            int currentAdviceIndex;
            do{
                currentAdviceIndex = new Random().nextInt(advices.size());
            } while (shownAdvices.contains(currentAdviceIndex));

            // Agrega el índice al conjunto de consejos mostrados
            shownAdvices.add(currentAdviceIndex);

            // Si todos los consejos han sido mostrados, reinicializa el conjunto
            if (shownAdvices.size() == advices.size()) {
                shownAdvices.clear();
            }

            String advice = advices.get(currentAdviceIndex);
            textViewFragment.setText(advice);
        }
    }
    private void addAdvice(){
        View popupView = getLayoutInflater().inflate(R.layout.addpopup, null);

        // Encuentra las vistas dentro del diseño inflado
        EditText textBox = popupView.findViewById(R.id.editTextId);
        Button btnAceptar = popupView.findViewById(R.id.guardar);
        Button btnCancelar = popupView.findViewById(R.id.cerrar);

        // Configura un clic para el botón "Aceptar"
        btnAceptar.setOnClickListener(view -> {
            // Obtiene el texto ingresado por el usuario
            String textoIngresado = textBox.getText().toString();
            // Guardamos el consejo en la lista local (assets es solo de lectura)
            advices.add(textoIngresado);
            consejosManager.addAdvise(textoIngresado, this.getActivity());
            // Cierra el popup
            alertDialog.dismiss();
        });

        // Configura un clic para el botón "Cancelar"
        btnCancelar.setOnClickListener(v -> alertDialog.dismiss());

        // Crea un objeto AlertDialog con el diseño inflado
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(popupView);

        // Muestra el popup
        alertDialog = builder.create();
        alertDialog.show();
    }
    private void showObjectives(){
        View popupView = getLayoutInflater().inflate(R.layout.objpopup, null);

        // Encuentra las vistas dentro del diseño inflado
        TextView textday = popupView.findViewById(R.id.tbDay);
        TextView textweek = popupView.findViewById(R.id.tbWeek);
        TextView textmonth = popupView.findViewById(R.id.tbMonth);
        Button btnCancelar = popupView.findViewById(R.id.cerrar);
        int p = objectives.getPerfil();
        if(p==2){
            textday.setText("Usa la aplicación unos días antes de obtener un objetivo");
            textmonth.setText("");
            textweek.setText("");
        }
        else {
            int[] o = objectives.getPrevision(p);
            String[] m = objectives.toString(o);
            textday.setText(m[0]);
            textweek.setText(m[1]);
            textmonth.setText(m[2]);
        }
        // Configura un clic para el botón "Aceptar"


        // Configura un clic para el botón "Cancelar"
        btnCancelar.setOnClickListener(v -> alertDialog.dismiss());

        // Crea un objeto AlertDialog con el diseño inflado
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(popupView);

        // Muestra el popup
        alertDialog = builder.create();
        alertDialog.show();
    }
}
