package main;

import java.util.List;

public class GradeBook {
    private final DBConnection db;

    public GradeBook(DBConnection db) {
        this.db = db;
    }

    public double averageByUser(User user) {
        List<Grade> grades = db.getGradesByUser(user);
        if (grades.isEmpty()) return 0.0;
        return grades.stream()
                .mapToInt(Grade::getGradeValue)
                .average()
                .orElse(0.0);
    }

    public double averageByClass(String className) {
        List<User> users = db.getUsers().stream()
                .filter(u -> u.getClassName().equals(className))
                .toList();

        double sum = 0;
        int count = 0;

        for (User user : users) {
            for (Grade g : db.getGradesByUser(user)) {
                sum += g.getGradeValue();
                count++;
            }
        }

        return count == 0 ? 0.0 : sum / count;
    }

    public double averageBySubject(String subject) {
        double sum = 0;
        int count = 0;

        for (User user : db.getUsers()) {
            for (Grade g : db.getGradesByUser(user)) {
                if (g.getSubject().equalsIgnoreCase(subject)) {
                    sum += g.getGradeValue();
                    count++;
                }
            }
        }

        return count == 0 ? 0.0 : sum / count;
    }
}