package hk.ust.cse.comp3021.lab3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BusCompanyTest {
    private static BusCompany c1;
    private static BusCompany c2;
    private static final String COMPANY1 = "KMB";
    private static final String COMPANY2 = "NWB";

    private static int totalCompanies = 0;

    @BeforeEach
    void resetCompany() {
        c1 = new BusCompany(COMPANY1);
        c2 = new BusCompany(COMPANY2);
        totalCompanies += 2;
    }

    @Test
    void testGetNames() {
        assertEquals(COMPANY1, c1.getName());
        assertEquals(COMPANY2, c2.getName());
    }

    @Test
    void testGetNumCompanies() {
        assertEquals(totalCompanies, BusCompany.getNumCompanies());
    }

    @Test
    void testNumCompaniesWithGarbageCollection() {
        new BusCompany("temp");
        System.gc();
        assertEquals(totalCompanies + 1, BusCompany.getNumCompanies());
    }

    @Test
    void testFailureAddDuplicate() {
        assertTrue(c1.createAndAddBus(1, "BMW"));
        assertFalse(c1.createAndAddBus(1, "Volvo"));
    }

    @Test
    void testSuccessAddDuplicateIdDifferentCompanies() {
        assertTrue(c1.createAndAddBus(1, "BMW"));
        assertTrue(c2.createAndAddBus(1, "BMW"));
    }

    @Test
    void testNumBuses() {
        assertTrue(c1.createAndAddBus(1, "BMW"));
        assertTrue(c1.createAndAddBus(2, "BMW"));
        assertEquals(2, c1.getNumBuses());
        assertTrue(c1.createAndAddBus(3, "BMW"));
        assertTrue(c1.createAndAddBus(4, "BMW"));
        assertTrue(c1.createAndAddBus(5, "BMW"));
        assertEquals(5, c1.getNumBuses());
    }

    @Test
    void testModels() {
        assertArrayEquals(new String[]{}, c1.getModels());

        c1.createAndAddBus(1, "BMW");
        c1.createAndAddBus(2, "BMW");
        assertArrayEquals(new String[]{"BMW"}, c1.getModels());

        c1.createAndAddBus(1, "Volvo");
        assertArrayEquals(new String[]{"BMW"}, c1.getModels());
    }

    @Test
    void testGetBus() {
        c1.createAndAddBus(1, "BMW");
        c1.createAndAddBus(2, "Volvo");
        var bus = c1.getBusByID(1);
        assertNotNull(bus);
        bus = c1.getBusByID(999);
        assertNull(bus);
    }

    @Test
    void testRemoveAllBuses() {
        assertTrue(c1.createAndAddBus(1, "BMW"));
        assertTrue(c2.createAndAddBus(0, "Volvo"));
        assertEquals(1, c1.getNumBuses());
        assertEquals(1, c2.getNumBuses());
        c1.removeAllBuses();
        assertEquals(0, c1.getNumBuses());
        assertEquals(1, c2.getNumBuses());
    }
}
