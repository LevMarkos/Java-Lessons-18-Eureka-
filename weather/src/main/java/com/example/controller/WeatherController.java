package com.example.controller;

import com.example.model.Weather;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private List<Weather> weatherList = new ArrayList<>(Arrays.asList(
            new Weather(0, "10°C", "Cloudy", "Moscow"),
            new Weather(1, "8°C", "Rain", "Ufa"),
            new Weather(2, "5°C", "Snow", "Ulyanovsk"),
            new Weather(3, "12°C", "Clear", "Abakan")
    ));

    @GetMapping("/id/{id}")
    public ResponseEntity<Weather> getWeatherById(@PathVariable int id) {
        for (Weather weather : weatherList) {
            if (weather.getId() == id) {
                return ResponseEntity.ok(weather);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<Weather> getWeatherByLocation(@PathVariable String location) {
        for (Weather weather : weatherList) {
            if (weather.getLocation().equalsIgnoreCase(location)) {
                return ResponseEntity.ok(weather);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
