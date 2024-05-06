package com.example.procesossoftware;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.widget.TextView;
import android.util.Log;

@RunWith(AndroidJUnit4.class)
public class Fragment_s1Test {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    // Contador de recursos inactivos para sincronizar con la interfaz de usuario
    private CountingIdlingResource countingIdlingResource;

    @Before
    public void setUp() {
        // Registra el recurso inactivo antes de cada prueba
        countingIdlingResource = new CountingIdlingResource("FragmentLoad");
        IdlingRegistry.getInstance().register(countingIdlingResource);
    }

    @After
    public void tearDown() {
        // Deregistra el recurso inactivo después de cada prueba
        IdlingRegistry.getInstance().unregister(countingIdlingResource);
    }

    @Test
    public void testTextoCigarrosFumados() {
        // Muestra el Fragment_s1
        mostrarFragmento(new Fragment_s1());

        // Verifica que el texto del número de cigarros fumados sea correcto
        Espresso.onView(ViewMatchers.withId(R.id.textViewFragment))
                .check(ViewAssertions.matches(ViewMatchers.withText("  Cigarros fumados   \n \n 0\n")));
    }

    @Test
    public void testTextoDiasSinFumar() {
        // Muestra el Fragment_s1
        mostrarFragmento(new Fragment_s1());

        // Verifica que el texto del número de días sin fumar sea correcto
        Espresso.onView(ViewMatchers.withId(R.id.textView))
                .check(ViewAssertions.matches(ViewMatchers.withText("  Días sin fumar   \n \n6\n")));
    }

    @Test
    public void testIncrementoCigarrosFumados() {
        // Muestra el Fragment_s1
        mostrarFragmento(new Fragment_s1());

        // Realiza un clic en el ImageButton para simular el incremento de cigarros fumados
        Espresso.onView(withId(R.id.imageButton)).perform(click());

        // Verifica que el texto del número de cigarros fumados se haya actualizado correctamente
        Espresso.onView(ViewMatchers.withId(R.id.textViewFragment))
                .check(ViewAssertions.matches(ViewMatchers.withText("  Cigarros fumados   \n \n 1\n")));
    }

    // Método para mostrar un fragmento en una actividad de prueba
    private void mostrarFragmento(Fragment fragment) {
        // Obtiene una instancia de la actividad de la regla de actividad
        activityScenarioRule.getScenario().onActivity(activity -> {
            // Inicia una transacción de fragmentos
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
            // Reemplaza cualquier fragmento que actualmente esté en el contenedor con este fragmento,
            // y añade la transacción a la pila de atrás
            transaction.replace(R.id.fragment_container, fragment);
            transaction.addToBackStack(null);
            // Envía la transacción
            transaction.commit();
        });
    }
}
