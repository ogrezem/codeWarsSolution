package ru.ogrezem.codeWarsSolution;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ogrezem.codeWarsSolution.other.OddSorter;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class OddSorterTest {

    @BeforeEach
    void initTest() {

    }

    @Test
    void sortArray1() {
        int[] array = {5, 3, 2, 8, 1, 4};
        int[] expectedArray = {1, 3, 2, 8, 5, 4};
        assertArrayEquals(expectedArray, OddSorter.sortArray(array));
    }

    @Test
    void sortArray2() {
        int[] array = {5, 3, 1, 8, 0};
        int[] expectedArray = {1, 3, 5, 8, 0};
        assertArrayEquals(expectedArray, OddSorter.sortArray(array));
    }

    @Test
    void sortArray3() {
        int[] array = {};
        int[] expectedArray = {};
        assertArrayEquals(expectedArray, OddSorter.sortArray(array));
    }

    @Test
    void sortArray4() {
        int[] array = {6, 2, 66, 8, 10, 4};
        int[] expectedArray = {6, 2, 66, 8, 10, 4};
        assertArrayEquals(expectedArray, OddSorter.sortArray(array));
    }

    @Test
    void sortArray5() {
        int[] array = {6, 2, 66, 8, 10, 4};
        int[] expectedArray = {6, 2, 66, 8, 10, 4};
        assertArrayEquals(expectedArray, OddSorter.sortArray(array));
    }
}