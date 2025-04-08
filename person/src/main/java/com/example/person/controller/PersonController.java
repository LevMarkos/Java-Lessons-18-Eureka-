package com.example.person.controller;

import com.example.person.model.User;
import com.example.person.model.Weather;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private List<User> users = new ArrayList<>(Arrays.asList(
            new User(0, "Ivan", "Ivanovich", "Ivanov", LocalDate.of(1999, 2, 3), "Moscow"),
            new User(1, "Petr", "Petrovich", "Petrov", LocalDate.of(2002, 2, 2), "Ufa"),
            new User(2, "Evgeny", "Vasilievich", "Vasin", LocalDate.of(2005, 4, 8), "Ulyanovsk"),
            new User(3, "Maxim", "Yakovlevich", "Okopskiy", LocalDate.of(1978, 6, 5), "Abakan")
    ));

    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        if (id < 0 || id >= users.size()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(users.get(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        users.add(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        if (id < 0 || id >= users.size()) {
            return ResponseEntity.notFound().build();
        }
        users.set(id, user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        if (id < 0 || id >= users.size()) {
            return ResponseEntity.notFound().build();
        }
        users.remove(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/weather")
    public ResponseEntity<Object> getUserAndWeather(@PathVariable int id) {
        if (id < 0 || id >= users.size()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ошибка: Пользователь с ID " + id + " не найден.");
        }
        User user = users.get(id);
        String location = user.getLocation();
        String weatherServiceUrl = "http://localhost:8083/weather/location/" + location;
        ResponseEntity<Weather> weatherResponse;
        try {
            weatherResponse = new RestTemplate().getForEntity(weatherServiceUrl, Weather.class);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ошибка: Не удалось получить данные о погоде. Причина: " + e.getMessage());
        }
        if (weatherResponse.getStatusCode() == HttpStatus.OK) {
            Weather weather = weatherResponse.getBody();
            return ResponseEntity.ok(new UserWeatherResponse(user, weather));
        } else {
            return ResponseEntity.status(weatherResponse.getStatusCode())
                    .body("Ошибка: Не удалось получить данные о погоде для локации " + location + ". Код статуса: " + weatherResponse.getStatusCode());
        }
    }

    static class UserWeatherResponse {
        private User user;
        private Weather weather;

        public UserWeatherResponse(User user, Weather weather) {
            this.user = user;
            this.weather = weather;
        }

        public User getUser() {
            return user;
        }

        public Weather getWeather() {
            return weather;
        }
    }
}