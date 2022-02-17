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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
/*        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
        Analizy result = new Analizy();
        result.unavailable("server.log", "target.csv");
    }
}

