package com.example.repository;

import com.example.model.Geodata;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeodataRepository extends CrudRepository<Geodata, Integer> {
    Optional<Geodata> findByName(String name);
}