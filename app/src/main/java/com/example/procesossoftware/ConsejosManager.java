package com.example.procesossoftware;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsejosManager {
    private ArrayList<String> advices;
    AssetManager assetManager;
    public ConsejosManager(Context context){
        assetManager = context.getAssets();
        loadAdvices();
    }
    public void loadAdvices() {
        advices = new ArrayList<>();

        try {
            InputStream inputStream = assetManager.open("consejos.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null) {
                advices.add(line);
            }

            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<String> getAdvices() {
        return advices;
    }
}
