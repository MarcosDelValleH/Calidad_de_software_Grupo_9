package com.example.procesossoftware;
import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class PreferencesManagerTest {

    private PreferencesManager preferencesManager;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        preferencesManager = new PreferencesManager(context);
    }
    @Test
    public void testGetRegWhenNotSet() {
        Registro registro = new Registro();
        preferencesManager.setReg(registro);
        // Get the Registro object from the PreferencesManager without setting it
        Registro retrievedRegistro = preferencesManager.getReg();

        // Check if retrievedRegistro is not null
        assertNotNull("Registro object retrieved is not null", retrievedRegistro);

        // Verify that the retrievedRegistro has default values
        // For example, if the default dailyCigaretteAverage is 8 and default numDias is 0:
        assertEquals(8, retrievedRegistro.getDailyCigaretteAverage());
        assertEquals(0, retrievedRegistro.getNumDias());
    }
    @Test
    public void testSetAndGetNumDias() {
        // Create a Registro object for testing
        Registro registro = new Registro();
        // Set the number of days
        registro.setNumDias(5);
        // Set the Registro object using the PreferencesManager
        preferencesManager.setReg(registro);
        // Get the Registro object from the PreferencesManager
        Registro retrievedRegistro = preferencesManager.getReg();
        // Get the number of days from the PreferencesManager
        // Check if retrievedRegistro is not null
        assertNotNull("Registro object retrieved is not null", retrievedRegistro);


        // Check if retrievedNumDias is equal to the value set
        assertEquals(5, retrievedRegistro.getNumDias());
    }

    @Test
    public void testSetAndGetDailyCigaretteAverage() {
        // Create a Registro object for testing
        Registro registro = new Registro();
        registro.setDailyCigaretteAverage(10);
        // Set the Registro object using the PreferencesManager
        preferencesManager.setReg(registro);

        // Get the Registro object from the PreferencesManager
        Registro retrievedRegistro = preferencesManager.getReg();
        // Check if retrievedRegistro is not null
        assertNotNull("Registro object retrieved is not null", retrievedRegistro);
        // Check if the retrievedRegistro has the same values as the original Registro object
        assertEquals(10, retrievedRegistro.getDailyCigaretteAverage());
    }

}
