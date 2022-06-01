package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class House {
    private boolean target;
    private int rooms;
    private String street;
    private Owner owner;
    private String[] systemSafe;

    public House(boolean target, int rooms, String street, Owner owner, String[] systemSafe) {
        this.target = target;
        this.rooms = rooms;
        this.street = street;
        this.owner = owner;
        this.systemSafe = systemSafe;
    }

    public boolean isTarget() {
        return target;
    }

    public void setTarget(boolean target) {
        this.target = target;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String[] getSystemSafe() {
        return systemSafe;
    }

    public void setSystemSafe(String[] systemSafe) {
        this.systemSafe = systemSafe;
    }

    @Override
    public String toString() {
        return "House{" + "target=" + target
                + ", rooms=" + rooms
                + ", street='" + street + '\''
                + ", owner=" + owner
                + ", systemSafe=" + Arrays.toString(systemSafe)
                + '}';
    }

    /**
     * 1/
     * Преобразуем объект house в json-строку.
     * Модифицируем json-строку
     * 2/
     * Преобразуйте объекты в JSONObject и json-строку с помощью библиотеки JSON-Java (org.json)
     * @param args
     */
    public static void main(String[] args) {

        final House house = new House(true, 4,
                "Red", new Owner("Nik", "Ivanov", "02.02.1995"),
                new String[] {"Fire", "Alarm "});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(house));

        final String houseModStr =
                "{"
                        + "\"target\": false,"
                        + "\"rooms\" : 5,"
                        + "\"street\" : \"Black\","
                        + "\"owner\":"
                        + "{"
                        + "\"name\" : \"Ben\","
                        + "\"surname\" : \"Lar\","
                        + "\"dateBr\" : \"02.02.2000\""
                        + "},"
                        + "\"systemSafe\" :"
                        + "[\"Fire\", \"Free\"]"
                        + "}";
        final House houseMod = gson.fromJson(houseModStr, House.class);
        System.out.println(houseMod);

        JSONObject jsonObject = new JSONObject();

        JSONObject jsonOwner = new JSONObject("{"
                + "\"name\" : \"Ben\","
                + "\"surname\" : \"Lar\","
                + "\"dateBr\" : \"02.02.2000\""
                + "}");

        String[] systemSafe = new String[]{"Fire"};

        JSONArray jsonSystemSafe = new JSONArray(systemSafe);
        jsonObject.put("target", house.isTarget());
        jsonObject.put("rooms", house.getRooms());
        jsonObject.put("street", house.getStreet());
        jsonObject.put("owner", jsonOwner);
        jsonObject.put("systemSafe", jsonSystemSafe);

        System.out.println(jsonObject);
        System.out.println(new JSONObject(house).toString());
    }
}
