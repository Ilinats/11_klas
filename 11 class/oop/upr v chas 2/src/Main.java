import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Task task1 = new Task("Question 1?", Arrays.asList("A", "B", "C"), 5, "A");
        Task task2 = new Task("Question 2?", Arrays.asList("A", "B", "C"), 10, "B");

        Test test = new Test("2025-01-01", "Group 1", Arrays.asList(task1, task2));

        Student student1 = new Student(1, "Alice", test, Arrays.asList("A", "B"));
        Student student2 = new Student(2, "Bob", test, Arrays.asList("A", "C"));

        List<Student> students = Arrays.asList(student1, student2);

        students.sort(Comparator.comparingInt(Student::calculateScore).reversed().thenComparing(Student::getName));

        for (Student student : students) {
            System.out.println(student);
        }
    }
}