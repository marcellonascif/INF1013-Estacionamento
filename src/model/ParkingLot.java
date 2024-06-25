package model;

public class ParkingLot {
    private int availableSpots;

    public ParkingLot(int availableSpots) {
        this.availableSpots = availableSpots;
    }

    public void registerInOut(Vehicle vehicle) {
        availableSpots--;

        System.out.println("Entrada registrada para veículo com placa: " + vehicle.getPlate());
    }

    public boolean isFull(){
        return availableSpots > 0;
    }


}
