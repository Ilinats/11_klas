package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LogProcessor {

    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LogAnalysisStrategy strategy = chooseStrategy(scanner);

        List<String> resourceFiles = Arrays.asList("log_file_1.txt", "log_file_2.txt");
        LogAnalyzer analyzer = new LogAnalyzer(strategy);

        try {
            processResourcesInParallel(resourceFiles, analyzer);
        } catch (InterruptedException e) {
            System.out.println("Обработката беше прекъсната: " + e.getMessage());
            Thread.currentThread().interrupt();
        }

        System.out.println(analyzer.getResults());

        scanner.close();
    }

    private static LogAnalysisStrategy chooseStrategy(Scanner scanner) {
        System.out.println("Изберете стратегия за анализ:");
        System.out.println("1. Брой съобщения по ниво");
        System.out.println("2. Търсене на съобщения по ключова дума");

        int choice = 0;
        while (choice != 1 && choice != 2) {
            System.out.print("Вашият избор (1 или 2): ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice != 1 && choice != 2) {
                    System.out.println("Невалиден избор. Моля, въведете 1 или 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Невалиден избор. Моля, въведете 1 или 2.");
            }
        }

        if (choice == 1) {
            return new CountByLevelStrategy();
        } else {
            System.out.print("Въведете ключова дума за търсене: ");
            String keyword = scanner.nextLine();
            return new FindKeywordStrategy(keyword);
        }
    }

    private static void processResourcesInParallel(List<String> resourceFiles, LogAnalyzer analyzer) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        for (String resourceName : resourceFiles) {
            executor.submit(() -> {
                try {
                    processResourceFile(resourceName, analyzer);
                } catch (IOException e) {
                    System.out.println("Грешка при обработка на ресурс " + resourceName + ": " + e.getMessage());
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }

    private static void processResourceFile(String resourceName, LogAnalyzer analyzer) throws IOException {
        ClassLoader classLoader = LogProcessor.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(resourceName)) {
            if (inputStream == null) {
                System.out.println("Ресурсът " + resourceName + " не е намерен.");
                return;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                LogEntry entry = parseLogEntry(line);
                if (entry != null) {
                    analyzer.analyze(entry);
                }
            }
        }
    }

    private static LogEntry parseLogEntry(String line) {
        try {
            String timestamp = line.substring(0, 23);

            int levelStart = 24;
            int levelEnd = line.indexOf(' ', levelStart);
            if (levelEnd == -1) return null;

            String level = line.substring(levelStart, levelEnd);

            int classStart = levelEnd + 1;
            int classEnd = line.indexOf(' ', classStart);
            if (classEnd == -1) return null;

            String sourceClass = line.substring(classStart, classEnd);
            String message = line.substring(classEnd + 1);

            return new LogEntry(timestamp, level, sourceClass, message);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Неправилен формат на лога: " + line);
            return null;
        }
    }
}