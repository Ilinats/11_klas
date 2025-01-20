public class Car extends Vehicle {
    double baseRate;

    public Car(String brand, String model, double baseRate) {
        super(brand, model);
        this.baseRate = baseRate;
    }

    @Override
    public double calculateDailyRate() {
        return baseRate;
    }

    @Override
    public String toString() {
        return "Car: " + "brand= " + brand + ", model= " + model + ", baseRate= " + calculateDailyRate();
    }
}
