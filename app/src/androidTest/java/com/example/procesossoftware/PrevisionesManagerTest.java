package com.example.procesossoftware;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


@RunWith(AndroidJUnit4.class)
public class PrevisionesManagerTest {


    // Helper method to create a Registro object for testing
    private Registro createRegistroForTest(int semanaInstalado, Integer [] semana) {
        Registro registro = new Registro();
        registro.lastWeek = 4; // Set lastWeek to 4 for testing
        registro.lastDay = 7; // Set lastDay to 7 for testing
        registro.semanaInstalado = semanaInstalado; // Set semanaInstalado for testing
        registro.reg.clear();
        // Add sample data to reg list for testing
        registro.reg.add(semana);
        return registro;
    }

    // Tests for getPerfil method
    @Test
    public void testGetPerfil_JustStarted() {
        // Create a Registro object for testing
        Integer[] semana = new Integer[]{4, 0, 0, 0, 0, 0, 0, 0}; // Sample data for one week
        Registro registro = createRegistroForTest(3, semana);
        // Create a PrevisionesManager object for testing
        PrevisionesManager previsionesManager = new PrevisionesManager(registro);
        // Call getPerfil method
        int perfil = previsionesManager.getPerfil();
        // Assert that the result matches the expected value: 2
        assertEquals(2, perfil);
    }

    @Test
    @Given("Un fumador casual")
    public void testGetPerfil_SporadicSmoker() {
        // Create a Registro object for testing
        Integer[] semana = new Integer[]{4, 0, 1, 0, 1, 0, 1, 0}; // Sample data for one week as a sporadic smoker
        Registro registro = createRegistroForTest(1, semana);
        // Create a PrevisionesManager object for testing
        PrevisionesManager previsionesManager = new PrevisionesManager(registro);
        // Call getPerfil method
        int perfil = previsionesManager.getPerfil();
        // Assert that the result matches the expected value: 1
        assertEquals(1, perfil);
    }

    @Test
    @Given("Un fumador diario")
    public void testGetPerfil_DailySmoker() {
        // Create a Registro object for testing
        Integer[] semana = new Integer[]{4, 5, 1, 2, 1, 3, 1, 4}; // Sample data for one week as a daily smoker
        Registro registro = createRegistroForTest(0, semana);
        // Create a PrevisionesManager object for testing
        PrevisionesManager previsionesManager = new PrevisionesManager(registro);
        // Call getPerfil method
        int perfil = previsionesManager.getPerfil();
        // Assert that the result matches the expected value: 0
        assertEquals(0, perfil);
    }


    // Tests for getPrevision method
    @Test
    @Then("Espero una prevision media")
    public void testGetPrevision_SporadicSmoker() {
        // Create a Registro object for testing
        Integer[] semana = new Integer[]{4, 0, 1, 0, 1, 0, 1, 0}; // Sample data for one week as a sporadic smoker
        Registro registro = createRegistroForTest(1, semana);
        // Create a PrevisionesManager object for testing
        PrevisionesManager previsionesManager = new PrevisionesManager(registro);
        // Call getPrevision method
        int[] prevision = previsionesManager.getPrevision(1); // Profile of sporadic smoker
        // Assert that the result matches the expected value
        // prevision[0] = 0
        // prevision[1] = (0 + 1 + 0 + 1 + 0 + 1) * 0.7
        // prevision[2] = (0 + 1 + 0 + 1 + 0 + 1) * 0.6 * 4
        assertArrayEquals(new int[]{0, 2, 7}, prevision);
    }

    @Test
    @Then("Espero una prevision mala")
    public void testGetPrevision_DailySmoker() {
        // Create a Registro object for testing
        Integer[] semana = new Integer[]{4, 5, 1, 2, 1, 3, 1, 4}; // Sample data for one week as a daily smoker
        Registro registro = createRegistroForTest(0, semana);
        // Create a PrevisionesManager object for testing
        PrevisionesManager previsionesManager = new PrevisionesManager(registro);
        // Call getPrevision method
        int[] prevision = previsionesManager.getPrevision(0); // Profile of daily smoker
        // Assert that the result matches the expected value:
        // prevision[0] = 0
        // prevision[1] = (5 + 1 + 2 + 1 + 3 + 1) * 0.8
        // prevision[2] = (5 + 1 + 2 + 1 + 3 + 1) * 0.7 * 4
        assertArrayEquals(new int[]{0, 10, 36}, prevision);
    }

}
