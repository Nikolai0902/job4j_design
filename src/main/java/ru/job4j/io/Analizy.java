package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            boolean isWork = true;
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] split = line.split(" ");
                if (isWork && ((split[0].equals("400")) || (split[0].equals("500")))) {
                    out.print(split[1] + ";");
                    isWork = false;
                } else if ((!isWork && ((split[0].equals("200")) || (split[0].equals("300"))))) {
                    out.println(split[1]);
                    isWork = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy result = new Analizy();
        result.unavailable("server.log", "target.csv");
    }
}

