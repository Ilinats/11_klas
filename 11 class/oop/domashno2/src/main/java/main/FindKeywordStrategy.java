package main;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class FindKeywordStrategy implements LogAnalysisStrategy {

    private final String keyword;
    private final Set<String> matchingLogs;

    public FindKeywordStrategy(String keyword) {
        this.keyword = keyword.toLowerCase();
        this.matchingLogs = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    @Override
    public void analyze(LogEntry entry) {
        if (entry.getLevel().toLowerCase().contains(keyword)) {
            matchingLogs.add(entry.toString());
            return;
        }

        String message = entry.getMessage().toLowerCase();
        if (message.contains(keyword)) {
            matchingLogs.add(entry.toString());
        }
    }

    @Override
    public String getResults() {
        StringBuilder result = new StringBuilder("--- Резултати от анализ: Съобщения, съдържащи \"" + keyword + "\" ---\n");

        if (matchingLogs.isEmpty()) {
            result.append("Няма намерени съобщения, съдържащи ключовата дума.");
        } else {
            matchingLogs.forEach(log -> result.append(log).append("\n"));
        }

        return result.toString();
    }
}