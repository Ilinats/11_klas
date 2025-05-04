package main;

public class LogAnalyzer {

    private final LogAnalysisStrategy strategy;

    public LogAnalyzer(LogAnalysisStrategy strategy) {
        this.strategy = strategy;
    }

    public void analyze(LogEntry entry) {
        strategy.analyze(entry);
    }

    public String getResults() {
        return strategy.getResults();
    }
}