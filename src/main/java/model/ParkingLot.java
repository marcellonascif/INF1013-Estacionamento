package model;

public class ParkingLot {
    private int availableSpots;

    public ParkingLot(int availableSpots) {
        this.availableSpots = availableSpots;
    }

    public void registerIn(Vehicle vehicle) {
        availableSpots--;

        System.out.println("Entrada registrada para veículo com placa: " + vehicle.getPlate());
        System.out.println("Vagas disponíveis: " + availableSpots);
    }

    public void registerOut(Vehicle vehicle) {
        availableSpots++;

        System.out.println("Saída registrada para veículo com placa: " + vehicle.getPlate());
        System.out.println("Vagas disponíveis: " + availableSpots);
    }

    public boolean isFull(){
        return availableSpots == 0;
    }
}
