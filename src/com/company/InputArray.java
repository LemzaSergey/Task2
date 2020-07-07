package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class InputArray {
    private Errors errors;

    public InputArray(String version) {
        this.errors = new Errors(version);
    }


    public int checkInt(String line) throws NumberFormatException {
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            line = errors.checkIntError(line);
            return Integer.parseInt(line);
        }
    }

    public String[][] toTwoDimensionalArrayString(String[] array) {
        String[][] array2 = new String[array.length][];
        for (int i = 0; i < array.length; i++) {
            array2[i] = array[i].split("\\s+");
        }
        return array2;
    }

    public List<List<String>> toTwoDimensionalListString(String[] array) {

        String[][] twoDimensionalArray = toTwoDimensionalArrayString(array);
        return ArrayToList(twoDimensionalArray);
    }


    public List<List<String>> ArrayToList(String[][] array) {
        List<String> lineList = new ArrayList<>();
        List<List<String>> mainList = new ArrayList<>();
        for (String[] lineArray : array) {
            for (String s : lineArray) {
                lineList.add(s);
            }
            mainList.add(lineList);
            lineList = new ArrayList<>();
        }
        return mainList;
    }

    public static int[] separationPartArray(List<List<Integer>> IntegerArr, int num) {

        if (Errors.workingListIntegerRedundant(IntegerArr)) {
            return new int[]{};
        }

        int[] listIntegerArr = new int[IntegerArr.get(num).size()];
        for (int i = 0; i < IntegerArr.get(num).size(); i++) {
            listIntegerArr[i] = IntegerArr.get(num).get(i);
        }
        return listIntegerArr;
    }

    public static int[] converterDoubleLinkedListToIntArr(MyDoublyLinkedListNode<Integer> head) {

        int[] result = new int[MyListUtils.getSize(head) - 1];
        int i = 0;

        head = head.getNext();
        for (MyDoublyLinkedListNode<Integer> curr = head; curr != null; curr = curr.getNext()) {

            result[i] = head.getValue();
            i++;
            head = head.getNext();
        }
        return result;
    }
}
