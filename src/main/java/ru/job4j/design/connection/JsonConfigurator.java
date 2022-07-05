package ru.job4j.design.connection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonConfigurator {
    public Gson get() {
        return new GsonBuilder().create();
    }
}
