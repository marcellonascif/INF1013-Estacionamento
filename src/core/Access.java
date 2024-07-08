package core;

import static util.JSONReader.reader;

import java.util.ArrayList;
import java.util.List;

public class Access {
    private List<Vehicle> registeredVehicles;
    private List<Vehicle> vehiclesInside;

    public Access() {
        this.registeredVehicles = new ArrayList<Vehicle>();
        this.vehiclesInside = new ArrayList<Vehicle>();
    }

    public void open() {
        System.out.println("abrir");
    }

    public void close() {
        System.out.println("fechar");
    }

    public List<Vehicle> getRegisteredVehiclesJSON() {
        return reader("assets/vehicles.json");
    }

    public Vehicle isVehicleRegistered(String plate) {
        for (Vehicle vehicle : this.registeredVehicles) {
            if (vehicle.getPlate().equals(plate)) {
                return vehicle;
            }
        }

        return null;
    }

    public boolean isVehicleInside(String plate) {
        for (Vehicle vehicle : this.vehiclesInside) {
            if (vehicle.getPlate().equals(plate)) {
                return true;
            }
        }

        return false;
    }

    public void registerIn(Vehicle vehicle, ParkingLot parkingLot) {
        this.vehiclesInside.add(vehicle);
        parkingLot.registerIn(vehicle);
    }

    public void registerOut(Vehicle vehicle, ParkingLot parkingLot) {
        this.vehiclesInside.remove(vehicle);
        parkingLot.registerOut(vehicle);
    }

    public void setRegisteredVehicles(List<Vehicle> registeredVehicles) {
        this.registeredVehicles = registeredVehicles;
    }

    public List<Vehicle> getRegisteredVehicles() {
        return this.registeredVehicles;
    }

    public void setVehiclesInside(List<Vehicle> vehiclesInside) {
        this.vehiclesInside = vehiclesInside;
    }

    public List<Vehicle> getVehiclesInside() {
        return this.vehiclesInside;
    }
}
