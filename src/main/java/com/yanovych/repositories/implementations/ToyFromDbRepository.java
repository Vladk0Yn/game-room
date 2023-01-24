package com.yanovych.repositories.implementations;

import com.yanovych.entities.Room;
import com.yanovych.entities.Toy;
import com.yanovych.repositories.interfaces.ToyRepository;

import java.util.List;

public class ToyFromDbRepository implements ToyRepository {
    private static ToyFromDbRepository instance = null;
    private ToyFromDbRepository() {

    }
    public static ToyFromDbRepository getInstance() {
        if (instance == null) {
            instance = new ToyFromDbRepository();
        }
        return instance;
    }
    @Override
    public Toy getToyById(Long id) {
        return null;
    }

    @Override
    public List<Toy> getAllToys() {
        return null;
    }

    @Override
    public void addToy(Toy toy) {

    }

    @Override
    public void addToyToRoom(Toy toy, Room room) {

    }

    @Override
    public void updateToy(Toy toy) {

    }
}
