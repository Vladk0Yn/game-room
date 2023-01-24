package com.yanovych.repositories.implementations;

import com.yanovych.entities.Child;
import com.yanovych.entities.Room;
import com.yanovych.entities.Toy;
import com.yanovych.helpers.ObjectFileReader;
import com.yanovych.helpers.ObjectFileWriter;
import com.yanovych.repositories.interfaces.RoomRepository;

import java.util.List;

public class RoomFromDbRepository implements RoomRepository {
    private static RoomFromDbRepository instance = null;
    private RoomFromDbRepository() {

    }
    public static RoomFromDbRepository getInstance() {
        if (instance == null) {
            instance = new RoomFromDbRepository();
        }
        return instance;
    }
    @Override
    public Room getRoomById(Long id) {
        return null;
    }

    @Override
    public List<Room> getAllRooms() {
        return null;
    }

    @Override
    public void addRoom(Room room) {

    }

    @Override
    public void addChildToRoom(Child child, Room room) {

    }

    @Override
    public void removeChildFromRoom(Child child, Room room) {

    }

    @Override
    public void addToyToRoom(Toy toy, Room room) {

    }

    @Override
    public void removeToyFromRoom(Toy toy, Room room) {

    }

    @Override
    public void updateRoom(Room room) {

    }
}
