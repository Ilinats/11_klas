import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Vehicle> vehicles = new ArrayList<>();

        vehicles.add(new Car("Toyota", "Corolla", 101.0));
        vehicles.add(new Car("Honda", "Civic", 55.0));
        vehicles.add(new Car("BMW", "i5", 120.0));
        vehicles.add(new Car("RedBull", "MaxVerstappen", 65.0));

        vehicles.add(new Van("Mercedes", "LewisHamilton", 100.0));
        vehicles.add(new Van("Ford", "Nz", 98.0));
        vehicles.add(new Van("BMW", "Paknz", 220.0));
        vehicles.add(new Van("Renault", "Otnovonz", 67.0));
//
//        for(Vehicle vehicle : vehicles) {
//            System.out.println(vehicle.brand + " " + vehicle.model + ": " + vehicle.calculateDailyRate());
//        }

        List<Vehicle> expensiveVehicles = vehicles.stream()
                .filter(vehicle -> vehicle.calculateDailyRate() > 100.0)
                .toList();
        expensiveVehicles.forEach(vehicle ->
                System.out.println(vehicle.toString())
        );

        System.out.println("-------------------------------------------------");

        List<Vehicle> sortedVehicles = vehicles.stream()
                .sorted(Comparator.comparingDouble(Vehicle::calculateDailyRate).reversed())
                .toList();
        sortedVehicles.forEach(vehicle ->
                System.out.println(vehicle.toString()));

        System.out.println("-------------------------------------------------");

        List<String> vehicleStrings = vehicles.stream()
                .map(vehicle -> "Марка: " + vehicle.brand + ", Модел: " + vehicle.model + ", Дневна такса: " + vehicle.calculateDailyRate())
                .toList();
        vehicleStrings.forEach(System.out::println);

        System.out.println("-------------------------------------------------");

        OptionalDouble averageRate = vehicles.stream().mapToDouble(Vehicle::calculateDailyRate).average();
        double minRate = vehicles.stream().mapToDouble(Vehicle::calculateDailyRate).min().orElse(0);
        double maxRate = vehicles.stream().mapToDouble(Vehicle::calculateDailyRate).max().orElse(0);
        double sumRate = vehicles.stream().mapToDouble(Vehicle::calculateDailyRate).sum();
        long count = vehicles.size();

        System.out.println("Средна дневна такса: " + averageRate.orElse(0));
        System.out.println("Минимална дневна такса: " + minRate);
        System.out.println("Максимална дневна такса: " + maxRate);
        System.out.println("Сума от всички дневни такси: " + sumRate);
        System.out.println("Брой превозни средства: " + count);

        System.out.println("-------------------------------------------------");

        boolean above100 = vehicles.stream().anyMatch(vehicle -> vehicle.calculateDailyRate() > 100);
        boolean allAbove30 = vehicles.stream().allMatch(vehicle -> vehicle.calculateDailyRate() > 30);
        boolean below20 = vehicles.stream().noneMatch(vehicle -> vehicle.calculateDailyRate() < 20);

        System.out.println("дневна такса над 100?: " + above100);
        System.out.println("всички дневна такса над 30?: " + allAbove30);
        System.out.println("няма с дневна такса под 20?: " + below20);

        System.out.println("-------------------------------------------------");

        List<Vehicle> thirdAndFourthCheapest = vehicles.stream()
                .sorted(Comparator.comparingDouble(Vehicle::calculateDailyRate))
                .skip(2)
                .limit(2)
                .toList();
        thirdAndFourthCheapest.forEach(vehicle ->
                System.out.println(vehicle.toString()));
    }
}