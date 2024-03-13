package com.example.procesossoftware;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Fragment_s1 extends Fragment {

    private TextView textViewFragment;
    private TextView cont;
    private String newTextPending;
    private Integer[] semana;
    private int dia;
    private Registro r;
    private boolean flag;
    CalendarManager calendarManager;

    ConsejosManager consejosManager;

    private PreferencesManager preferencesManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_s1, container, false);
        flag=false;

        // Obtener referencia al ImageButton
        ImageButton imageButton = view.findViewById(R.id.imageButton);

        // Obtener referencia al TextView del fragmento
        textViewFragment = view.findViewById(R.id.textViewFragment);
        cont = view.findViewById(R.id.textView);
        preferencesManager = new PreferencesManager(getContext());
        calendarManager = new CalendarManager();
        consejosManager = new ConsejosManager(getContext());
        //recuperamos la información de cigarros
        r = preferencesManager.getReg();
        if(r==null){ //no se ha creado todavia
            r = new Registro();
            preferencesManager.setReg(r);
            flag=true;
        }
        else{ //actualizamos el registro al dia y la fecha actual
            flag = calendarManager.updateRegistro(r);
            preferencesManager.setReg(r);
        }
        semana = r.reg.get(r.reg.size()-1);
        dia = r.lastDay;

        // Mostramos el numero de cigarros que llevamos
        textViewFragment.setText("  Cigarros fumados   \n \n " + semana[r.lastDay]+"\n");
        cont.setText("  Días sin fumar   \n \n"+r.numDias + "\n");

        if (newTextPending != null) {
            newTextPending = null; // Resetea el texto pendiente
        }

        // Configurar un OnClickListener para el ImageButton
        imageButton.setOnClickListener(v -> {
            // Manejar añadir cigarros fumados cuando se toca el ImageButton
            semana[dia]++;
            changeText("  Cigarros fumados   \n \n " + semana[dia]+"\n");
            r.setNumDias(0);
            cont.setText("  Dias sin fumar   \n \n"+r.numDias + "\n");
            preferencesManager.setReg(r); // Guardar cambios en las preferencias compartidas

            setMessage(view, r);
        });

        if(flag){
            CreatePopUp(inflater);
        }

        setMessage(view, r);

        return view;
    }

    public void setMessage(View viewgroup, Registro registro){
        TextView title = viewgroup.findViewById(R.id.title);
        TextView text = viewgroup.findViewById(R.id.puedes_comprar);

        Double balance = Estimaciones.ahorroTotal(registro);
        title.setText("Has ahorrado " + String.format("%.02f", balance) + "€");
        String balanceString = Estimaciones.getBalance(balance);
        text.setText(balanceString);
        if (balance >= 0) {
            String strBalance = String.format("%.02f", balance);
            title.setText("Has ahorrado " + String.format("%.02f", balance) + "€");
            text.setText(balanceString);
        } else {
            String strBalance = String.format("%.02f", -balance);
            title.setText("Has gastado " +  strBalance + "€ más de lo normal.\n\nNo te desmotives, conseguirás que ese número sea positivo.");
            text.setText("");
        }
    }


    private void CreatePopUp(LayoutInflater inflater) {
        View popUpView = inflater.inflate(R.layout.fragment_pop_up, null);
        TextView textView = popUpView.findViewById(R.id.randomAdvice);

        // Leer consejos desde el archivo de texto
        consejosManager.loadAdvices();
        List<String> consejosList = consejosManager.getAdvices();

        // Obtener un consejo aleatorio
        if (!consejosList.isEmpty()) {
            Random random = new Random();
            int result = random.nextInt(consejosList.size());
            textView.setText(consejosList.get(result));
        }
        // Crea un objeto AlertDialog con el diseño inflado
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(popUpView);

        // Muestra el popup
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        Button button = popUpView.findViewById(R.id.cerrar);
        button.setOnClickListener(v -> alertDialog.dismiss());
    }


    // Método público para cambiar el texto del TextView
    public void changeText(String newText) {
        if (textViewFragment != null) {
            textViewFragment.setText(newText);
        }
        else {
            newTextPending = newText; // Almacena el nuevo texto si el fragmento no se ha inflado aún
        }
    }



}