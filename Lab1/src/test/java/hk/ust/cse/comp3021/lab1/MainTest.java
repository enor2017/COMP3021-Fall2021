package hk.ust.cse.comp3021.lab1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    void add() {
        assertEquals(3, Main.add(1, 2));
    }
}
