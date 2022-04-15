package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private boolean available;
    private final List<String> log = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    private void checkAvailable(String input) {
        if (STOP.equals(input)) {
            available = false;
        } else if (CONTINUE.equals(input)) {
            available = true;
        }
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            available = true;
            String input = in.readLine();
            checkAvailable(input);
            List<String> answers = readPhrases();
            log.add(input);
            while (!OUT.equals(input)) {
                if (available) {
                    String answer = answers.get(new Random().nextInt(answers.size()));
                    log.add(answer);
                    System.out.println(answer);
                }
                input = in.readLine();
                log.add(input);
                checkAvailable(input);
            }
            saveLog();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            reader.lines().forEach(phrases::add);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        return phrases;
    }

    private void saveLog() {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(
                new FileWriter(path, StandardCharsets.UTF_8)))) {
            log.forEach(out::println);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(
                "./data/logfile.txt", "./data/bot_answers.txt");
        cc.run();
    }
}
