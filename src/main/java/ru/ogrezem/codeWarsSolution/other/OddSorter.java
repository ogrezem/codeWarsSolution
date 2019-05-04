package ru.ogrezem.codeWarsSolution.other;

import java.util.Arrays;

public class OddSorter {
    public static int[] sortArray(int[] array) {
        if (Arrays.equals(array, new int[]{}))
            return array;
        while (true) {
            boolean arrayChanged = false;
            int currentNumKey = 0;
            int currentNum = array[currentNumKey];
            if (currentNumKey != array.length - 1 && currentNum % 2 != 0) {
                for (int nextNumKey = currentNumKey + 1; nextNumKey < array.length; nextNumKey++) {
                    int nextNum = array[nextNumKey];
                    if (nextNum % 2 != 0 && currentNum > nextNum) {
                        array[currentNumKey] = nextNum;
                        array[nextNumKey] = currentNum;
                        arrayChanged = true;
                        currentNumKey = nextNumKey;
                        StringBuilder sb = new StringBuilder();//
                        sb.append("[");//
                        for (int i = 0; i < array.length; i++) {//
                            sb.append(array[i]);//
                            if (i != array.length - 1)//
                                sb.append(", ");//
                        }//
                        sb.append("]");//
                        System.out.println(sb);//
                    }
                }
            } else
                return array;
            if (!arrayChanged)
                return array;
        }
    }
}
