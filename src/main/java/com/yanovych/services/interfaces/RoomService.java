package com.yanovych.services.interfaces;

import com.yanovych.entities.Child;
import com.yanovych.entities.Room;

import java.util.List;

public interface RoomService {
    Room getRoomById(Long id);
    Room getRoomForChildAgeById(Long id, Child child);
    void createRoom(Room room);
    List<Room> getAllRooms();
    void addChildToRoom(Child child, Room room);
    void removeChildFromRoom(Child child, Room room);
    List<Room> getAvailableRoomsForChildAge(Child child);
}
