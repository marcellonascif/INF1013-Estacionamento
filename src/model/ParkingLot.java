package model;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<Vehicle> registeredVehicles;
    private int availableSpots;

    public ParkingLot(int availableSpots) {
        this.registeredVehicles = new ArrayList<>();
        this.availableSpots = availableSpots;
    }

    public void getRegisteredVehiclesJSON() {
//        this.registeredVehicles = registeredVehicles;
//        USAR O RESGATE DE JSON AQUI
    }
}
