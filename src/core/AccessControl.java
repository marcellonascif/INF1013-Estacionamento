package core;

public class AccessControl {

    public static void accessControlIn(String plate, ParkingLot parkingLot, Access access) {
        System.out.println("Tentativa de acesso para o veículo com placa " + plate);

        Vehicle vehicle = access.isVehicleRegistered(plate);
        if (vehicle == null) {
            System.out.println("Veículo com placa " + plate + " não está cadastrado\n");
            return;
        }

        if (access.isVehicleInside(plate)) {
            System.out.println("Veículo com placa " + plate + " já está dentro do estacionamento\n");
            return;
        }

        if (parkingLot.isFull()) {
            System.out.println("Estacionamento lotado. Acesso negado.\n");
            return;
        }

        access.open();
        access.registerIn(vehicle, parkingLot);
        access.close();
        System.out.println("Veículo com placa " + plate + " entrou no estacionamento.\n");
    }

    public static void accessControlOut(String plate, ParkingLot parkingLot, Access access) {
        System.out.println("Tentativa de saída para o veículo com placa " + plate);

        Vehicle vehicle = access.isVehicleRegistered(plate);
        if (vehicle == null) {
            System.out.println("Veículo com placa " + plate + " não está cadastrado\n");
            return;
        }

        if (!access.isVehicleInside(plate)) {
            System.out.println("Veículo com placa " + plate + " não está dentro do estacionamento\n");
            return;
        }

        access.open();
        access.registerOut(vehicle, parkingLot);
        access.close();
        System.out.println("Veículo com placa " + plate + " saiu do estacionamento.\n");
    }

    public static void main(String[] args) {
        ParkingLot parkingCar = new ParkingLot(3);

        Access accessCar = new Access();


        accessCar.setRegisteredVehicles(accessCar.getRegisteredVehiclesJSON());

        // Simulando tentativas de entrada e saída
        AccessControl.accessControlIn("ABC-1A23", parkingCar, accessCar);
        AccessControl.accessControlIn("DEF-4B56", parkingCar, accessCar);
        AccessControl.accessControlIn("CCC-9012", parkingCar, accessCar);
        AccessControl.accessControlIn("GHI-7C89", parkingCar, accessCar);
        AccessControl.accessControlIn("JKL-0D12", parkingCar, accessCar);

        AccessControl.accessControlOut("ABC-1A23", parkingCar, accessCar);
        AccessControl.accessControlIn("DEF-4B56", parkingCar, accessCar);
        AccessControl.accessControlOut("YZA-5I67", parkingCar, accessCar);


    }
}
