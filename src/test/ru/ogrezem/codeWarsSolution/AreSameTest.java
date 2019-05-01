package ru.ogrezem.codeWarsSolution;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AreSameTest {

    @Test
    void compTrue() {
        int[] a = new int[]{121, 144, 19, 161, 19, 144, 19, 11};
        int[] b = new int[]{121, 14641, 20736, 361, 25921, 361, 20736, 361};
        assertTrue(AreSame.comp(a, b));
    }

    @Test
    void compFalse() {
        int[] c = new int[]{122, 144, 19, 161, 19, 144, 19, 11};
        int[] d = new int[]{121, 14641, 20736, 361, 25921, 361, 20736, 361};
        assertFalse(AreSame.comp(c, d));
    }
}