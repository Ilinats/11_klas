package vehiclerent.vehicle;

import vehiclerent.exception.InvalidRentingPeriodException;

import java.time.Duration;
import java.time.LocalDateTime;

public class Car extends Vehicle {

    private static final int SEATS_PRICE = 5;
    private final FuelType fuelType;
    private final int numberOfSeats;
    private double pricePerWeek;
    private double pricePerDay;
    private double pricePerHour;

    public Car(String id, String model, FuelType fuelType, int numberOfSeats, double pricePerWeek, double pricePerDay, double pricePerHour) {
        super(id, model);
        this.fuelType = fuelType;
        this.numberOfSeats = numberOfSeats;
        this.pricePerWeek = pricePerWeek;
        this.pricePerDay = pricePerDay;
        this.pricePerHour = pricePerHour;
    }

    @Override
    public double calculateRentalPrice(LocalDateTime startOfRent, LocalDateTime endOfRent) throws InvalidRentingPeriodException {
        if (startOfRent.isAfter(endOfRent)) {
            throw new InvalidRentingPeriodException("End date is before start date");
        }

        Duration duration = Duration.between(startOfRent, endOfRent);
        long weeks = duration.toDays() / 7;
        long days = duration.toDays() % 7;
        long hours = duration.toHours() % 24 + 1;

        return weeks * pricePerWeek + days * pricePerDay + hours * pricePerHour + numberOfSeats * SEATS_PRICE + fuelType.ordinal() * duration.toDays() + 1;
    }
}
