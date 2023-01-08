package com.yanovych.repository.interfaces;

import com.yanovych.entities.Child;
import com.yanovych.entities.Room;
import com.yanovych.entities.Toy;

import java.util.List;

public interface ToyRepository {
    Toy getToyById(Long id);
    List<Toy> getAllToys();
    void addToy(Toy toy);
    void addToyToRoom(Toy toy, Room room);
    void removeToyFromRoom(Toy toy, Room room);
    void updateToy(Toy toy);
}
