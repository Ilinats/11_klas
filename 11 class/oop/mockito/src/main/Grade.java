package main;

public class Grade {
    private String subject;
    private int gradeValue;

    public Grade(String subject, int gradeValue) {
        this.subject = subject;
        this.gradeValue = gradeValue;
    }

    public String getSubject() {
        return subject;
    }

    public int getGradeValue() {
        return gradeValue;
    }
}