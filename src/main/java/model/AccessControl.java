package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
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

    public static List<Vehicle> loadVehiclesFromJSON(String filename) {
        Gson gson = new Gson();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    AccessControl.class.getClassLoader().getResourceAsStream(filename)));
            Type vehicleListType = new TypeToken<ArrayList<Vehicle>>() {}.getType();
            List<Vehicle> vehicles = gson.fromJson(bufferedReader, vehicleListType);
            bufferedReader.close();
            return vehicles;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Retorna uma lista vazia em caso de erro
        }
    }

    public static void main(String[] args) {
        ParkingLot parkingCar = new ParkingLot(3);

        Access accessCar = new Access();

        // Carregar veículos a partir de um arquivo JSON
        List<Vehicle> listCar = loadVehiclesFromJSON("vehicles.json");
        accessCar.setRegisteredVehicles(listCar);

        // Simulando tentativas de acesso
        AccessControl.accessControlIn("ABC-1A23", parkingCar, accessCar);
        AccessControl.accessControlIn("DEF-4B56", parkingCar, accessCar);
        AccessControl.accessControlIn("GHI-7C89", parkingCar, accessCar);
        AccessControl.accessControlIn("CCC-2204", parkingCar, accessCar);
        AccessControl.accessControlIn("DDD-1806", parkingCar, accessCar);

        AccessControl.accessControlOut("ABC-1A23", parkingCar, accessCar);
        AccessControl.accessControlIn("MNO-3E45", parkingCar, accessCar);

        // Exibir veículos registrados e veículos dentro do estacionamento como JSON
        System.out.println("Registered Vehicles JSON: " + accessCar.getRegisteredVehiclesJSON());
        System.out.println("Vehicles Inside JSON: " + accessCar.getInsideVehiclesJSON());
    }
}
