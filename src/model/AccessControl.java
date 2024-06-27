package model;

//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        // faz sentido essa verificacao? acho que sim pois pode ter acontecido algum problema na entrada do veiculo
        
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

//    public static ParkingLot loadJSON(String filename) {
//        String jsonText = "";
//        try {
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
//
//            String line;
//            while((line = bufferedReader.readLine()) != null){
//                jsonText += line + "\n";
//            }
//
//            bufferedReader.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new ParkingLot(100); // Valor default para vagas disponíveis
//        }
//    }

    public static void main(String[] args) {
        ParkingLot parkingCar = new ParkingLot(3);

        Access accessCar = new Access();

        Vehicle car1 = new Car("AAA1234");
        Vehicle car2 = new Car("BBB5678");
        Vehicle car3 = new Car("CCC2204");
        Vehicle car4 = new Car("DDD1806");

        List<Vehicle> listCar = new ArrayList<>();
        listCar.add(car1);
        listCar.add(car2);
        listCar.add(car3);
        listCar.add(car4);

        accessCar.setRegisteredVehicles(listCar);

        // Simulando tentativas de acesso
        AccessControl.accessControlIn("AAA1234", parkingCar, accessCar);
        AccessControl.accessControlIn("BBB5678", parkingCar, accessCar);
        AccessControl.accessControlIn("CCC9012", parkingCar, accessCar);
        AccessControl.accessControlIn("CCC2204", parkingCar, accessCar);
        AccessControl.accessControlIn("DDD1806", parkingCar, accessCar);

        AccessControl.accessControlOut("AAA1234", parkingCar, accessCar);
        AccessControl.accessControlIn("DDD1806", parkingCar, accessCar);


    }
}
