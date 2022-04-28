package ru.job4j.io;

import java.io.*;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        String str = "";
        boolean flag = true;
        List<String> log = new ArrayList<>();
        while (!OUT.equals(str)) {
            System.out.println("Введите фразу: ");
            str = scanner.nextLine();
            log.add(str);
            if (str.equals(STOP)) {
                flag = false;
            }
            if (str.equals(CONTINUE)) {
                flag = true;
            }
            if (!str.equals(OUT) && !str.equals(STOP) && flag) {
                String bot = readPhrases().get(random.nextInt(readPhrases().size()));
                System.out.println(bot);
                log.add(bot);
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> stringList = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                stringList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringList;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            pw.println(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("end.log", "botAnswers.log");
        cc.run();
    }
}
