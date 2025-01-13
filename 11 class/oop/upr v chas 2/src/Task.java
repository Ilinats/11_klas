import java.util.List;

public class Task {
    private final String condition;
    private final List<String> answers;
    private final int points;
    private final String correctAnswer;

    public Task(String condition, List<String> answers, int points, String correctAnswer) {
        this.condition = condition;
        this.answers = answers;
        this.points = points;
        this.correctAnswer = correctAnswer;
    }

    public int getPoints() {
        return points;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Condition: " + condition + ", Points: " + points + ", Correct Answer: " + correctAnswer;
    }
}