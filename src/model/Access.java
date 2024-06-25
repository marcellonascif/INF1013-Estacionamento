package model;

import java.util.ArrayList;
import java.util.List;

public class Access {
    private List<Vehicle> registeredVehicles;

    public Access() {
        this.registeredVehicles = new ArrayList<Vehicle>();
    }

    public void open(){
        System.out.println("abrir");
    }

    public void close(){
        System.out.println("fechar");
    }

    public void getRegisteredVehiclesJSON() {
//        this.registeredVehicles = registeredVehicles;
//        USAR O RESGATE DE JSON AQUI
    }

    public Vehicle isVehicleRegistered(String plate){
        for (Vehicle vehicle : registeredVehicles){
            if (vehicle.getPlate().equals(plate)){
                return vehicle;
            }
        }

        return null;
    }

    public void registerInOut(Vehicle vehicle, ParkingLot parkingLot){
        parkingLot.registerInOut(vehicle);
    }

    public void setRegisteredVehicles(List<Vehicle> registeredVehicles){
        this.registeredVehicles = registeredVehicles;
    }

    public List<Vehicle> getRegisteredVehicles(){
        return this.registeredVehicles;
    }
}
