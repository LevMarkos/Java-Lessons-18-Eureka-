package com.example.person.repository;

import com.example.person.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {
    private List<User> users = new ArrayList<>();
    }
