package com.example.procesossoftware;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ConsejosManager {
    private ArrayList<String> advices;
    AssetManager assetManager;

    public ConsejosManager(Context context, Activity activity) {
        assetManager = context.getAssets();
        loadAdvices(activity);
    }

    public void loadAdvices(Activity activity) {
        try {
            advices = new ArrayList<>();
            File archivoConsejos = new File(activity.getFilesDir(),"consejos.txt");
            if (!archivoConsejos.exists()) initConsejos(activity.getFilesDir().getPath()+ "/" + "consejos.txt");
            FileInputStream fis = activity.openFileInput("consejos.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String linea;
            while ((linea = reader.readLine()) != null) {
                advices.add(linea);
            }
            reader.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAdvices() {
        return advices;
    }

    public void addAdvise(String advice, Activity activity){
        advices.add(advice);
        try {
            FileOutputStream fos = activity.openFileOutput("consejos.txt", Context.MODE_APPEND);
            // Crear un BufferedWriter con FileWriter
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));

            // Escribir la frase en una nueva línea
            writer.newLine();
            writer.write(advice);
            // Cerrar el BufferedWriter
            writer.close();

        } catch (IOException e) {
            System.err.println("Error al añadir la frase: " + e.getMessage());
        }
    }

    public void initConsejos(String ruta){
        try {
            InputStream inputStream = assetManager.open("consejos.txt");
            OutputStream outputStream = new FileOutputStream(ruta);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            // Cerrar flujos
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
