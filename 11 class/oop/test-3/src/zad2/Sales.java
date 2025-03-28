package zad2;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Sales {
    private final ConcurrentHashMap<String, Double> revenue = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Integer> salesCount = new ConcurrentHashMap<>();

    public void processFile(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(filePath));
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Future<?>> futures = new ArrayList<>();

        for (String line : lines) {
            futures.add(executor.submit(() -> processLine(line)));
        }

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                System.out.println(e.getMessage());
            }
        }

        executor.shutdown();
    }

    private void processLine(String line) {
        String[] parts = line.split(",");
        String productId = parts[0];
        int quantity = Integer.parseInt(parts[2]);
        double price = Double.parseDouble(parts[3]);
        revenue.merge(productId, quantity * price, Double::sum);
        salesCount.merge(productId, quantity, Integer::sum);
    }

    public void saveResults() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/zad2/revenue.txt"))) {
            revenue.forEach((product, revenue) -> {
                try {
                    writer.write(product + ": " + revenue + "\n");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
        }

        List<String> topProducts = salesCount.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(10)
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.toList());

        Files.write(Path.of("src/zad2/top_products.txt"), topProducts);
    }

    public static void main(String[] args) throws Exception {
        Sales processor = new Sales();
        processor.processFile("src/zad2/test.txt");
        processor.saveResults();
    }
}