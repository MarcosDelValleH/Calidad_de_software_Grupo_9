package com.example.procesossoftware;

import static org.junit.Assert.assertEquals;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


@RunWith(AndroidJUnit4.class)
public class EstimacionesTest {

    @Given("Un usuario que fuma 5 cigarros al dia")
    public Registro crearRegistro(){
        // Create a Registro object for testing
        Registro registro = new Registro();
        registro.setDailyCigaretteAverage(5); // Set daily cigarette average to 5 for testing
        return registro;
    }

    @Test
    @Given("El registro de una semana")
    @Then("Quiero ver su ahorro semanal")
    public void testAhorroSemanal() {
        Registro registro = crearRegistro();
        registro.reg.clear();
        registro.reg.add( new Integer[]{0, 3, 7, 5, 0, 2, 0, 1});
        // Call ahorroSemanal method with the Registro object
        double result = Estimaciones.ahorroSemanal(registro);
        // Assert that the result matches the expected value:
        // ahorro semanal = (5 * 7 - 18) * 0.25 = 4.25
        assertEquals(4.25, result, 0.01);
    }

    @Test
    @Given("El registro de otra semana")
    @Then("Quiero ver su ahorro total")
    public void testAhorroTotal() {
        Registro registro = crearRegistro();
        registro.reg.clear();
        registro.reg.add(new Integer[]{0, 3, 7, 5, 0, 2, 0, 1}); // Sample data for one week
        registro.reg.add(new Integer[]{0, 2, 7, 5, 1, 5, 3, 0}); // Sample data for another week
        // Call ahorroTotal method with the Registro object
        double result = Estimaciones.ahorroTotal(registro);
        // Assert that the result matches the expected value:
        // ahorrototal = (5 * 7 - 18 + 5 * 7 - 23) * 0.25 = 7.25
        assertEquals(7.25, result, 0.01);
    }

    // Tests for getBalance method
    @Test
    @Given("Un ahorro de 25 euros")
    @Then("Espero una cena especial")
    public void testGetBalance_PositiveBalance() {
        // Call the method with a positive balance
        String result = Estimaciones.getBalance(25.0);
        // Assert that the result matches the expected value:
        // Con el dinero que te has ahorrado podrías tener una cena especial
        assertEquals("Con el dinero que te has ahorrado podrías tener una cena especial.", result);
    }

    @Test
    @Given("Un balance negativo")
    @Then("Espero que el sistema me anime")
    public void testGetBalance_NegativeBalance() {
        // Call the method with a negative balance
        String result = Estimaciones.getBalance(-50.0);
        // Assert that the result matches the expected value:
        // Has gastado 50.00€ más de lo normal.
        // No te desmotives, conseguirás que ese número sea positivo.
        assertEquals("Has gastado 50.00€ más de lo normal.\n\nNo te desmotives, conseguirás que ese número sea positivo.", result);
    }

    @Test
    @Given("Un ahorro de 15 euros")
    @Then("Espero ir al cine")
    public void testGetBalance_InBetweenBalance() {
        // Call the method with a balance in between the defined ranges
        String result = Estimaciones.getBalance(15.0);
        // Assert that the result matches the expected value:
        // Con el dinero que te has ahorrado podrías ir al cine e invitar a alguien.
        assertEquals("Con el dinero que te has ahorrado podrías ir al cine e invitar a alguien.", result);
    }

}
