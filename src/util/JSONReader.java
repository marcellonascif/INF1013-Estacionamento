package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import core.Vehicle;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JSONReader {

    public static List<Vehicle> reader(String file) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(file)) {
            Type vehicleListType = new TypeToken<List<Vehicle>>() {}.getType();
            return gson.fromJson(reader, vehicleListType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
