package com.example.procesossoftware;

import androidx.fragment.app.FragmentManager;
import androidx.test.core.app.ActivityScenario;

public class TestUtils {

    // Método para obtener el FragmentManager desde la actividad de prueba
    public static FragmentManager obtenerFragmentManager() {
        final FragmentManager[] fragmentManager = {null};
        ActivityScenario.launch(MainActivity.class).onActivity(activity -> {
            fragmentManager[0] = activity.getSupportFragmentManager();
        });
        return fragmentManager[0];
    }

    // Método para esperar un tiempo específico en Espresso
    public static void waitEspresso(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}