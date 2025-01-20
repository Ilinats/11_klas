public class Van extends Vehicle{
    double capacityCharge;

    public Van(String brand, String model, double capacityCharge) {
        super(brand, model);
        this.capacityCharge = capacityCharge;
    }

    @Override
    public double calculateDailyRate() {
        return 0.5 * capacityCharge;
    }

    @Override
    public String toString() {
        return "Van: " + "brand= " + brand + ", model= " + model + ", capacityCharge= " + calculateDailyRate();
    }
}
