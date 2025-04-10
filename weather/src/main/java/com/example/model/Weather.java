package com.example.model;

public class Weather {
    private int id;
    private String temperature;
    private String condition;
    private String location;

    public Weather(int id, String temperature, String condition, String location) {
        this.id = id;
        this.temperature = temperature;
        this.condition = condition;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getCondition() {
        return condition;
    }

    public String getLocation() {
        return location; // Добавлен геттер для location
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
