package com.yanovych.services.interfaces;

import com.yanovych.entities.Child;
import com.yanovych.entities.Toy;

import java.util.List;

public interface ToyService {
    Toy getToyById(Long id);
    void createToy(Toy toy);
    List<Toy> getAllToys();
    List<Toy> getToysWithoutRoom();
}
