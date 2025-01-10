import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Ili", 85),
                new Student("Sami", 42),
                new Student("Gabo", 70),
                new Student("Tanq", 95),
                new Student("Ivo", 33),
                new Student("SIsi", 58),
                new Student("Dani", 78),
                new Student("Kala", 47),
                new Student("Koko", 88),
                new Student("Acho", 66)
        );

        List<Student> passedStudents = students.stream()
                .filter(student -> student.getGrade() >= 50)
                .toList();
        System.out.println("Students who passed:");
        passedStudents.forEach(System.out::println);

        List<String> studentNames = students.stream()
                .map(Student::getName)
                .toList();
        System.out.println("All student names:");
        studentNames.forEach(System.out::println);

        List<Student> sortedStudents = students.stream()
                .sorted(Comparator.comparingInt(Student::getGrade).reversed()
                        .thenComparing(Student::getName))
                .toList();
        System.out.println("Sorted students:");
        sortedStudents.forEach(System.out::println);

        List<Student> topStudents = sortedStudents.stream()
                .limit(3)
                .toList();
        System.out.println("Top 3 students:");
        topStudents.forEach(System.out::println);

        IntStream gradesStream = students.stream().mapToInt(Student::getGrade);
        double average = gradesStream.average().orElse(0);
        System.out.println("Average grade: " + average);

        gradesStream = students.stream().mapToInt(Student::getGrade);
        int minGrade = gradesStream.min().orElse(0);
        System.out.println("Minimum grade: " + minGrade);

        gradesStream = students.stream().mapToInt(Student::getGrade);
        int maxGrade = gradesStream.max().orElse(0);
        System.out.println("Maximum grade: " + maxGrade);

        gradesStream = students.stream().mapToInt(Student::getGrade);
        int sumGrades = gradesStream.sum();
        System.out.println("Sum of grades: " + sumGrades);

        Map<Boolean, List<Student>> groupedByPassFail = students.stream()
                .collect(Collectors.groupingBy(student -> student.getGrade() >= 50));
        System.out.println("Grouped by pass/fail:");
        groupedByPassFail.forEach((passed, group) -> {
            System.out.println((passed ? "Passed:" : "Failed:") + " " + group);
        });

        String topStudentName = students.stream()
                .max(Comparator.comparingInt(Student::getGrade))
                .map(Student::getName)
                .orElse("No students");
        System.out.println("Top student: " + topStudentName);
    }
}