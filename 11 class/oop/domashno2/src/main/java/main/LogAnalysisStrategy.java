package main;

public interface LogAnalysisStrategy {

    void analyze(LogEntry entry);
    String getResults();
}