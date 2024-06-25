package model;

public class Catraca {
    public void open(){
        System.out.println("abrir");
    }

    public void close(){
        System.out.println("fechar");
    }

    public void registerInOut(Vehicle vehicle, ParkingLot parkingLot){
        parkingLot.registerInOut(vehicle);
    }
}
