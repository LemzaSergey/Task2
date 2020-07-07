package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkWithFile {

    public String[] fileToString(String nameFile) throws FileNotFoundException {
        FileReader fileReader = new FileReader(nameFile);
        Scanner scanFile = new Scanner(fileReader);
        List<String> listArray = new ArrayList<>();
        while (scanFile.hasNextLine()) {
            listArray.add(scanFile.nextLine());
        }
        return listArray.toArray(new String[0]);
    }

    public void saveArrayToFile(int[][] arr, String path) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for (int[] ints : arr) {
                for (int j : ints) {
                    bw.write(j + " ");
                }
                bw.newLine();
            }
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveArrayToFile(int[] arr, String path) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));

            for (int j : arr) {
                bw.write(j + " ");
            }

            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
