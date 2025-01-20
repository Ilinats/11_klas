import java.util.*;
import java.util.stream.Collectors;

public class EmployeePaymentSystem {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new FullTimeEmployee("Alice", 1, 72000));
        employees.add(new FullTimeEmployee("Bob", 2, 84000));
        employees.add(new FullTimeEmployee("Charlie", 3, 60000));
        employees.add(new PartTimeEmployee("David", 4, 25, 120));
        employees.add(new PartTimeEmployee("Eve", 5, 30, 100));
        employees.add(new PartTimeEmployee("Frank", 6, 20, 150));

        System.out.println("Employees with monthly pay > 3000:");
        employees.stream()
                .filter(e -> e.calculateMonthlyPay() > 3000)
                .forEach(e -> System.out.println(e.getName() + " - " + e.calculateMonthlyPay()));

        System.out.println("\nEmployees sorted by monthly pay (descending):");
        employees.stream()
                .sorted(Comparator.comparingDouble(Employee::calculateMonthlyPay).reversed())
                .forEach(e -> System.out.println(e.getName() + " - " + e.calculateMonthlyPay()));

        System.out.println("\nEmployees as strings:");
        List<String> employeeStrings = employees.stream()
                .map(e -> String.format("ID:%d, Name:%s, MonthlyPay:%.2f", e.getId(), e.getName(), e.calculateMonthlyPay()))
                .toList();
        employeeStrings.forEach(System.out::println);

        DoubleSummaryStatistics stats = employees.stream()
                .mapToDouble(Employee::calculateMonthlyPay)
                .summaryStatistics();
        System.out.println("\nSalary statistics:");
        System.out.println("Average: " + stats.getAverage());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Max: " + stats.getMax());
        System.out.println("Sum: " + stats.getSum());
        System.out.println("Count: " + stats.getCount());

        boolean anyAbove6000 = employees.stream().anyMatch(e -> e.calculateMonthlyPay() > 6000);
        boolean allAbove2000 = employees.stream().allMatch(e -> e.calculateMonthlyPay() > 2000);
        boolean noneBelow500 = employees.stream().noneMatch(e -> e.calculateMonthlyPay() < 500);
        System.out.println("\nMatch operations:");
        System.out.println("Any above 6000: " + anyAbove6000);
        System.out.println("All above 2000: " + allAbove2000);
        System.out.println("None below 500: " + noneBelow500);

        System.out.println("\nMiddle class employees:");
        employees.stream()
                .sorted(Comparator.comparingDouble(Employee::calculateMonthlyPay).reversed())
                .skip(2)
                .limit(3)
                .forEach(e -> System.out.println(e.getName() + " - " + e.calculateMonthlyPay()));

        double totalSalary = employees.stream()
                .map(Employee::calculateMonthlyPay)
                .reduce(0.0, Double::sum);
        System.out.println("\nTotal salary (using reduce): " + totalSalary);

        System.out.println("\nEmployees grouped by role:");
        Map<String, List<Employee>> groupedByRole = employees.stream()
                .collect(Collectors.groupingBy(Employee::getRole));
        groupedByRole.forEach((role, group) -> {
            System.out.println(role + ":");
            group.forEach(e -> System.out.println("  " + e.getName() + " - " + e.calculateMonthlyPay()));
        });

        System.out.println("\nFind employee by name or ID:");
        Optional<Employee> found = employees.stream()
                .filter(e -> e.getName().equals("Alice") || e.getId() == 2)
                .findFirst();
        found.ifPresentOrElse(
                e -> System.out.println("Found: " + e.getName() + " - " + e.calculateMonthlyPay()),
                () -> System.out.println("Employee not found."));
    }
}