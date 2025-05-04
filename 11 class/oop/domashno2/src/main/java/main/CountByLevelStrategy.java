package main;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CountByLevelStrategy implements LogAnalysisStrategy {

    private final ConcurrentHashMap<String, AtomicInteger> counters;

    public CountByLevelStrategy() {
        this.counters = new ConcurrentHashMap<>();
        this.counters.put("DEBUG", new AtomicInteger(0));
        this.counters.put("INFO", new AtomicInteger(0));
        this.counters.put("WARNING", new AtomicInteger(0));
        this.counters.put("ERROR", new AtomicInteger(0));
    }

    @Override
    public void analyze(LogEntry entry) {
        String level = entry.getLevel();

        counters.computeIfAbsent(level, k -> new AtomicInteger(0));
        counters.get(level).incrementAndGet();
    }

    @Override
    public String getResults() {
        StringBuilder result = new StringBuilder("--- Резултати от анализ: Брой съобщения по ниво ---\n");

        counters.forEach((level, count) -> {
            result.append(level).append(": ").append(count.get()).append("\n");
        });

        return result.toString();
    }
}