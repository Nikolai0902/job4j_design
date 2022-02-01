package ru.job4j.io;

import java.io.FileOutputStream;
import java.util.Arrays;

public class Matrix {
    public static int[][] matrix(int size) {
        int[][] table = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }

    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("matrix.txt")) {
            int[][] table = Matrix.matrix(5);
            for (int i = 0; i < table.length; i++) {
                out.write(Arrays.toString(table[i]).getBytes());
                out.write(System.lineSeparator().getBytes());
            }
            out.write(System.lineSeparator().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
