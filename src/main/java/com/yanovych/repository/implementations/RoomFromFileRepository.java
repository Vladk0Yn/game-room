package com.yanovych.repository.implementations;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yanovych.entities.Child;
import com.yanovych.entities.Room;
import com.yanovych.helpers.ObjectFileReader;
import com.yanovych.helpers.ObjectFileWriter;
import com.yanovych.repository.interfaces.RoomRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RoomFromFileRepository implements RoomRepository {

    private List<Room> rooms = this.getAllRooms();
    private static RoomFromFileRepository instance = null;

    private RoomFromFileRepository() {
    }

    public static RoomFromFileRepository getInstance() {
        if (instance == null) {
            instance = new RoomFromFileRepository();
        }
        return instance;
    }

    @Override
    public List<Room> getAllRooms() {
        String roomsListJson = ObjectFileReader.read("rooms.json");
        return new Gson().fromJson(roomsListJson, new TypeToken<List<Room>>(){}.getType());
    }

    @Override
    public void addRoom(Room room) {
        if (this.rooms == null || this.rooms.isEmpty()) {
            this.rooms  = new ArrayList<>();
            room.setId(1L);
        } else {
            Long lastRoomId = this.rooms.stream().max(Comparator.comparingLong(Room::getId)).get().getId();
            room.setId(++lastRoomId);
        }
        this.rooms.add(room);
        String roomsJsonFormat = new Gson().toJson(this.rooms);
        ObjectFileWriter.write("rooms.json", roomsJsonFormat, false);
    }

    @Override
    public void updateRoom(Room room) {
        for (int i = 0; i < this.rooms.size(); i++) {
            if (room.getId().equals(this.rooms.get(i).getId())) {
                this.rooms.set(i, room);
                String roomsJsonFormat = new Gson().toJson(this.rooms);
                ObjectFileWriter.write("rooms.json", roomsJsonFormat, false);
                break;
            }
        }
    }

    @Override
    public void addChildToRoom(Child child, Room room) {
        List<Child> childrenInRoom = room.getChildrenInRoom();
        if (childrenInRoom == null) {
            childrenInRoom = new ArrayList<>();
        }
        childrenInRoom.add(child);
        room.setChildrenInRoom(childrenInRoom);
        this.updateRoom(room);
    }
}
