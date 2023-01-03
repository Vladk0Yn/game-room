package com.yanovych.repository.interfaces;

import com.yanovych.entities.Child;
import com.yanovych.entities.Room;

import java.util.List;

public interface RoomRepository {
    Room getRoomById(Long id);
    List<Room> getAllRooms();
    void addRoom(Room room);
    void addChildToRoom(Child child, Room room);
    void removeChildFromRoom(Child child, Room room);
    void updateRoom(Room room);
}
