package tests;

import main.*;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LogProcessorTests {
    private File testLogFile;

    @BeforeEach
    void setUp() throws IOException {
        testLogFile = File.createTempFile("test_log", ".txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testLogFile))) {
            writer.write("2023-10-26 10:00:00.123 INFO com.example.UserService User logged in: user123\n");
            writer.write("2023-10-26 10:00:05.456 DEBUG com.example.DatabaseConnection Connection established\n");
            writer.write("2023-10-26 10:00:10.789 WARNING com.example.FileHandler File not found: config.ini\n");
            writer.write("2023-10-26 10:00:15.012 ERROR com.example.OrderProcessing Order processing failed: exception occurred\n");
            writer.write("2023-10-26 10:00:20.345 INFO com.example.ProductService Product added: product456\n");
        }
    }

    @AfterEach
    void tearDown() {
        if (testLogFile != null && testLogFile.exists()) {
            testLogFile.delete();
        }
    }

    @Test
    void testCountByLevelStrategy() throws IOException {
        CountByLevelStrategy strategy = new CountByLevelStrategy();
        LogAnalyzer analyzer = new LogAnalyzer(strategy);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testLogFile))) {
            writer.write("2023-10-26 10:00:00.123 INFO com.example.UserService User logged in: user123\n");
            writer.write("2023-10-26 10:00:05.456 DEBUG com.example.DatabaseConnection Connection established\n");
            writer.write("2023-10-26 10:00:10.789 WARNING com.example.FileHandler File not found: config.ini\n");
            writer.write("2023-10-26 10:00:15.012 ERROR com.example.OrderProcessing Order processing failed: exception occurred\n");
            writer.write("2023-10-26 10:00:20.345 INFO com.example.ProductService Product added: product456\n");
        }

        List<LogEntry> entries = new ArrayList<>();
        entries.add(new LogEntry("2023-10-26 10:00:00.123", "INFO", "com.example.UserService", "User logged in: user123"));
        entries.add(new LogEntry("2023-10-26 10:00:05.456", "DEBUG", "com.example.DatabaseConnection", "Connection established"));
        entries.add(new LogEntry("2023-10-26 10:00:10.789", "WARNING", "com.example.FileHandler", "File not found: config.ini"));
        entries.add(new LogEntry("2023-10-26 10:00:15.012", "ERROR", "com.example.OrderProcessing", "Order processing failed: exception occurred"));
        entries.add(new LogEntry("2023-10-26 10:00:20.345", "INFO", "com.example.ProductService", "Product added: product456"));

        for (LogEntry entry : entries) {
            analyzer.analyze(entry);
        }

        String results = analyzer.getResults();
        assertTrue(results.contains("INFO: 2"));
        assertTrue(results.contains("DEBUG: 1"));
        assertTrue(results.contains("WARNING: 1"));
        assertTrue(results.contains("ERROR: 1"));
    }

    @Test
    void testFindKeywordStrategy() {
        FindKeywordStrategy strategy = new FindKeywordStrategy("exception");
        LogAnalyzer analyzer = new LogAnalyzer(strategy);

        List<LogEntry> entries = new ArrayList<>();
        entries.add(new LogEntry("2023-10-26 10:00:00.123", "INFO", "com.example.UserService", "User logged in: user123"));
        entries.add(new LogEntry("2023-10-26 10:00:05.456", "DEBUG", "com.example.DatabaseConnection", "Connection established"));
        entries.add(new LogEntry("2023-10-26 10:00:10.789", "WARNING", "com.example.FileHandler", "File not found: config.ini"));
        entries.add(new LogEntry("2023-10-26 10:00:15.012", "ERROR", "com.example.OrderProcessing", "Order processing failed: exception occurred"));
        entries.add(new LogEntry("2023-10-26 10:00:20.345", "INFO", "com.example.ProductService", "Product added: product456"));

        for (LogEntry entry : entries) {
            analyzer.analyze(entry);
        }

        String results = analyzer.getResults();
        assertTrue(results.contains("Order processing failed: exception occurred"));
        assertFalse(results.contains("User logged in: user123"));
    }

    @Test
    void testThreadSafetyOfCountByLevelStrategy() throws InterruptedException {
        CountByLevelStrategy strategy = new CountByLevelStrategy();
        LogAnalyzer analyzer = new LogAnalyzer(strategy);

        List<LogEntry> entries = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            if (i % 4 == 0) {
                entries.add(new LogEntry("2023-10-26 10:00:00.123", "INFO", "com.example.Service", "Message " + i));
            } else if (i % 4 == 1) {
                entries.add(new LogEntry("2023-10-26 10:00:00.123", "DEBUG", "com.example.Service", "Message " + i));
            } else if (i % 4 == 2) {
                entries.add(new LogEntry("2023-10-26 10:00:00.123", "WARNING", "com.example.Service", "Message " + i));
            } else {
                entries.add(new LogEntry("2023-10-26 10:00:00.123", "ERROR", "com.example.Service", "Message " + i));
            }
        }

        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (LogEntry entry : entries) {
            executor.submit(() -> analyzer.analyze(entry));
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        String results = analyzer.getResults();
        assertTrue(results.contains("INFO: 250"));
        assertTrue(results.contains("DEBUG: 250"));
        assertTrue(results.contains("WARNING: 250"));
        assertTrue(results.contains("ERROR: 250"));
    }

    @Test
    void testThreadSafetyOfFindKeywordStrategy() throws InterruptedException {
        FindKeywordStrategy strategy = new FindKeywordStrategy("test");
        LogAnalyzer analyzer = new LogAnalyzer(strategy);

        List<LogEntry> entries = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            if (i % 2 == 0) {
                entries.add(new LogEntry("2023-10-26 10:00:00.123", "INFO", "com.example.Service", "This is a test message " + i));
            } else {
                entries.add(new LogEntry("2023-10-26 10:00:00.123", "INFO", "com.example.Service", "Regular message " + i));
            }
        }

        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (LogEntry entry : entries) {
            executor.submit(() -> analyzer.analyze(entry));
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        String results = analyzer.getResults();
        int matchCount = results.split("\n").length - 1;
        assertEquals(250, matchCount);
    }

    @Test
    void testParallelVsSequentialProcessing() {
        CountByLevelStrategy parallelStrategy = new CountByLevelStrategy();
        CountByLevelStrategy sequentialStrategy = new CountByLevelStrategy();

        LogAnalyzer parallelAnalyzer = new LogAnalyzer(parallelStrategy);
        LogAnalyzer sequentialAnalyzer = new LogAnalyzer(sequentialStrategy);

        List<LogEntry> entries = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String level = i % 4 == 0 ? "INFO" : i % 4 == 1 ? "DEBUG" : i % 4 == 2 ? "WARNING" : "ERROR";
            entries.add(new LogEntry("2023-10-26 10:00:00.123", level, "com.example.Service", "Message " + i));
        }

        try {
            ExecutorService executor = Executors.newFixedThreadPool(4);
            for (LogEntry entry : entries) {
                executor.submit(() -> parallelAnalyzer.analyze(entry));
            }
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            fail("Паралелната обработка беше прекъсната");
        }

        for (LogEntry entry : entries) {
            sequentialAnalyzer.analyze(entry);
        }

        assertEquals(sequentialAnalyzer.getResults(), parallelAnalyzer.getResults());
    }
}