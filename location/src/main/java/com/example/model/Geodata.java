package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Geodata {
        @Id
        @GeneratedValue
        int id;
        @NonNull
        private double longitude;
        @NonNull private double latitude;
        @NonNull private String name;

        public Object getLat() {
            return null;
        }

        public Object getLon() {
            return null;
        }
    }
