package ru.job4j.cache;

import java.io.IOException;
import java.util.Scanner;

public class Emulator {

    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        Emulator emulator = new Emulator();
        String directory = emulator.askStr("Укажите директорию: ");
        if (directory.contains("cache")) {
            directory = "C:\\projects\\job4j_design\\"
                    + "src\\main\\java\\ru\\job4j\\cache\\";
        }
        System.out.println(directory);
        String file = emulator.askStr("Укажите файл: ");
        if (file.contains("Address")) {
            file = "Address.txt";
        } else if (file.contains("Names")) {
            file = "Names.txt";
        }
        System.out.println(file + System.lineSeparator());
        System.out.println("Чтение файла: "
                + System.lineSeparator()
                + new DirFileCache(directory).get(file));
    }

    public String askStr(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }
}
