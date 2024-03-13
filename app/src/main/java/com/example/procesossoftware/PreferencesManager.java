package com.example.procesossoftware;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PreferencesManager extends Fragment {

    SharedPreferences sharedPreferences;
    public PreferencesManager(Context context){
        sharedPreferences = context.getSharedPreferences("MiSharedPreferences", Context.MODE_PRIVATE);
    }
    public Registro getReg(){
        String json = sharedPreferences.getString("registro2", null);
        if (json != null) {
            Gson gson = new GsonBuilder().create();
            Registro reg = gson.fromJson(json, Registro.class);
            return reg;
        }
        return null;
    }
    public void setReg(Registro reg){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(reg);
        editor.putString("registro2",json);
        editor.apply(); // Guarda los cambios
    }

}
