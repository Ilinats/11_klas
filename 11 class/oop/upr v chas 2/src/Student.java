import java.util.List;

public class Student {
    private final int number;
    private final String name;
    private final Test test;
    private final List<String> answers;

    public Student(int number, String name, Test test, List<String> answers) {
        this.number = number;
        this.name = name;
        this.test = test;
        this.answers = answers;
    }

    public int calculateScore() {
        int score = 0;
        List<Task> questions = test.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            if (i < answers.size() && questions.get(i).getCorrectAnswer().equals(answers.get(i))) {
                score += questions.get(i).getPoints();
            }
        }
        return score;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Number: " + number + ", Name: " + name + ", Score: " + calculateScore();
    }
}