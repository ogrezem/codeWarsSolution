package ru.ogrezem.codeWarsSolution;

import java.util.HashMap;

public class AreSame {

    public static boolean comp(int[] a, int[] b) {
        if (a.length != b.length) {
            System.err.println("Не совпадают по изначальной длине!");
            return false;
        }
        HashMap<Integer, Integer> aElementsAmounts = new HashMap<>();
        // makes HashMap where key is a number and value is amount of entries
        for (int aEl1 : a) {
            if (!aElementsAmounts.containsKey(aEl1)) {
                aElementsAmounts.put(aEl1, 0);
            }
            aElementsAmounts.replace(aEl1, aElementsAmounts.get(aEl1) + 1);
        }
        HashMap<Integer, Integer> bElementsAmounts = new HashMap<>();
        for (int bEl1 : b) {
            if (!bElementsAmounts.containsKey(bEl1)) {
                bElementsAmounts.put(bEl1, 0);
            }
            bElementsAmounts.replace(bEl1, bElementsAmounts.get(bEl1) + 1);
        }
        if (aElementsAmounts.size() != bElementsAmounts.size()) {
            System.err.println("Не совпадают по количеству групп по количествам элементов");//
            return false;
        }
//        System.out.println("printAmounts(aElementsAmounts);");//
//        printAmounts(aElementsAmounts);//
//        System.out.println("==================================");//
//        printAmounts(squareAmounts(aElementsAmounts));//
//        System.out.println("printAmounts(bElementsAmounts);");//
//        printAmounts(bElementsAmounts);//
        for (HashMap.Entry<Integer, Integer> aElementsAmountsEntry : aElementsAmounts.entrySet()) {
            int aKey = aElementsAmountsEntry.getKey();
            int aValue = aElementsAmountsEntry.getValue();
            int squaredAKey = (int) Math.pow(aKey, 2);
            if (!(bElementsAmounts.containsKey(squaredAKey) && bElementsAmounts.get(squaredAKey) == aValue)) {
//                System.err.println("Какому-то числу не нашлось пары ;c");//
//                System.err.println("bElementsAmounts.containsKey(aKey ^ 2): " + bElementsAmounts.containsKey(squaredAKey));//
//                System.err.println("bElementsAmounts.get(aKey ^ 2): " + bElementsAmounts.get(squaredAKey));//
                return false;
            }
        }
        return true;
    }

    private static void printAmounts(HashMap<Integer, Integer> amounts) {
        for (HashMap.Entry<Integer, Integer> amountsEntry : amounts.entrySet()) {
            System.out.println(amountsEntry.getKey() + " : " + amountsEntry.getValue());
        }
    }

    private static HashMap<Integer, Integer> squareAmounts(HashMap<Integer, Integer> amounts) {
        HashMap<Integer, Integer> squaredAmounts = new HashMap<>(amounts.size());
        for (HashMap.Entry<Integer, Integer> entry : amounts.entrySet()) {
            Integer oldKey = entry.getKey();
            Integer newKey = oldKey * oldKey;
            System.out.println("old key: " + oldKey + "; new key: " + newKey);//
            squaredAmounts.put(newKey, entry.getValue());
        }
        return squaredAmounts;
    }
}