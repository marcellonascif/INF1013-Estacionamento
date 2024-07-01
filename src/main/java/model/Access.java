package model;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class Access {
    private List<Vehicle> registeredVehicles;
    private List<Vehicle> vehiclesInside;

    public Access() {
        this.registeredVehicles = new ArrayList<Vehicle>();
        this.vehiclesInside = new ArrayList<Vehicle>();
    }

    public void open(){
        System.out.println("abrir");
    }

    public void close(){
        System.out.println("fechar");
    }

    public String getRegisteredVehiclesJSON() {
        Gson gson = new Gson();
        return gson.toJson(this.registeredVehicles);
    }

    public String getInsideVehiclesJSON() {
        Gson gson = new Gson();
        return gson.toJson(this.vehiclesInside);
    }

    public Vehicle isVehicleRegistered(String plate){
        for (Vehicle vehicle : registeredVehicles){
            if (vehicle.getPlate().equals(plate)){
                return vehicle;
            }
        }

        return null;
    }

    public boolean isVehicleInside(String plate){
        for (Vehicle vehicle : vehiclesInside){
            if (vehicle.getPlate().equals(plate)){
                return true;
            }
        }

        return false;
    }

    public void registerIn(Vehicle vehicle, ParkingLot parkingLot){
        vehiclesInside.add(vehicle);
        parkingLot.registerIn(vehicle);
    }

    public void registerOut(Vehicle vehicle, ParkingLot parkingLot){
        vehiclesInside.remove(vehicle);
        parkingLot.registerOut(vehicle);
    }

    public void setRegisteredVehicles(List<Vehicle> registeredVehicles){
        this.registeredVehicles = registeredVehicles;
    }

    public List<Vehicle> getRegisteredVehicles(){
        return this.registeredVehicles;
    }

    public void setVehiclesInside(List<Vehicle> vehiclesInside){
        this.vehiclesInside = vehiclesInside;
    }

    public List<Vehicle> getVehiclesInside(){
        return this.vehiclesInside;
    }
}
