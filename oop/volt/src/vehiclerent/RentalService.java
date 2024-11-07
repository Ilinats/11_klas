package vehiclerent;

import vehiclerent.driver.Driver;
import vehiclerent.exception.InvalidRentingPeriodException;
import vehiclerent.exception.VehicleAlreadyRentedException;
import vehiclerent.exception.VehicleNotRentedException;
import vehiclerent.vehicle.Vehicle;

import java.time.LocalDateTime;
import java.util.Map;

public class RentalService {
    private record DriverStartOfRent(Driver driver, LocalDateTime startOfRent) {
    }

    private Map<Vehicle, DriverStartOfRent> takenVehicles;

    public void rentVehicle(Driver driver, Vehicle vehicle, LocalDateTime startOfRent) throws VehicleAlreadyRentedException {
        if(driver == null || vehicle == null || startOfRent == null) {
            throw new IllegalArgumentException("Driver and vehicle must not be null");
        }

        if(takenVehicles.containsKey(vehicle)) {
            throw new VehicleAlreadyRentedException("Vehicle is already rented");
        }

        takenVehicles.put(vehicle, new DriverStartOfRent(driver, startOfRent));
    }

    public double returnVehicle(Vehicle vehicle, LocalDateTime endOfRent) throws InvalidRentingPeriodException, VehicleNotRentedException {
        if(vehicle == null || endOfRent == null) {
            throw new IllegalArgumentException("Vehicle and endOfRent must not be null");
        }

        if(!takenVehicles.containsKey(vehicle)) {
            throw new VehicleNotRentedException("Vehicle is not rented");
        }

        DriverStartOfRent driverStartOfRent = takenVehicles.get(vehicle);
        if(endOfRent.isBefore(driverStartOfRent.startOfRent)) {
            throw new InvalidRentingPeriodException("End date is before start date");
        }

        takenVehicles.remove(vehicle);

        return vehicle.calculateRentalPrice(driverStartOfRent.startOfRent, endOfRent) + driverStartOfRent.driver.group().ordinal();
    }
}