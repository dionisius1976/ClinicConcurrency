package ru.lesson.lessons;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by trit on 26.02.2016.
 */
public class ClinicTest {

    Clinic clinic;

    @Before
    public void setUp() throws Exception {
        clinic = new Clinic();
        clinic.getClients().add(new Client("Pavel", new Dog("Bobic")));
        clinic.getClients().add(new Client("Masha", new Cat("Murzik")));
        clinic.getClients().add(new Client("Oleg", new Dog("Verny")));
        clinic.getClients().add(new Client("Elena", new Cat("Kisa")));
    }

    @Test
    public void testIsClientExist() throws Exception {

        assertTrue(clinic.isClientExist("Masha"));
        assertFalse(clinic.isClientExist("Boris"));
    }

    @Test
    public void testIsPetExist() throws Exception {
        assertTrue(clinic.isPetExist("Bobic"));
        assertFalse(clinic.isPetExist("Ryzhik"));
    }

    @Test
    public void testFindClientByPetsName() throws Exception {
        assertTrue("Pavel".equals(clinic.findClientByPetsName("Bobic").getName()));
        assertFalse("Pavel".equals(clinic.findClientByPetsName("Verny").getName()));

    }

    @Test
    public void testFindPetByClientsName() throws Exception {
        assertEquals("Murzik", clinic.findPetByClientsName("Masha").getName());
        assertNull(clinic.findPetByClientsName("Khariton"));
    }

    @Test
    public void testEditClientsName() throws Exception {
        clinic.editClientsName("Pavel", "Ivan");
        assertTrue(clinic.isClientExist("Ivan"));
        assertFalse(clinic.isClientExist("Pavel"));

    }

    @Test
    public void testEditPetsName() throws Exception {
        clinic.editPetsName("Murzik", "Barsik");
        assertFalse(clinic.isPetExist("Murzik"));
        assertTrue(clinic.isPetExist("Barsik"));
    }

    @Test
    public void testDeleteClientByHisName() throws Exception {
        clinic.deleteClientByHisName("Oleg");
        assertFalse(clinic.isClientExist("Oleg"));

    }

    @Test
    public void testDeleteClientByPetsName() throws Exception {
        clinic.deleteClientByPetsName("Kisa");
        assertFalse(clinic.isPetExist("Kisa"));

    }
}