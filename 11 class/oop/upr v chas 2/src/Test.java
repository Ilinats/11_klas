import java.util.List;

public class Test {
    private String date;
    private String group;
    private List<Task> questions;

    public Test(String date, String group, List<Task> questions) {
        this.date = date;
        this.group = group;
        this.questions = questions;
    }

    public List<Task> getQuestions() {
        return questions;
    }

    @Override
    public String toString() {
        return "Date: " + date + ", Group: " + group + ", Questions: " + questions.size();
    }
}