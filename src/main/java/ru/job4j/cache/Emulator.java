package ru.job4j.cache;

public class Emulator {
    public static void main(String[] args) {
        DirFileCache dirFileCache = new DirFileCache("C:\\projects\\job4j_design\\"
                + "src\\main\\java\\ru\\job4j\\cache\\");
        System.out.println(dirFileCache.load("Names.txt"));
        System.out.println(dirFileCache.load("Names.txt"));
    }
}
