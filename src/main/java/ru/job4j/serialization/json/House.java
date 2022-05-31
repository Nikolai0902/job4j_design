package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

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

    @Override
    public String toString() {
        return "House{" + "target=" + target
                + ", rooms=" + rooms
                + ", street='" + street + '\''
                + ", owner=" + owner
                + ", systemSafe=" + Arrays.toString(systemSafe)
                + '}';
    }

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
    }
}
