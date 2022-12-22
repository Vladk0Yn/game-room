package com.yanovych.services.interfaces;

import com.yanovych.entities.Child;
import com.yanovych.entities.Room;

import java.util.List;

public interface RoomService {

    void createRoom(Room room);
    List<Room> getAllRooms();
    void addChildToRoom(Child child, Room room);
}
