package com.yanovych.repository.implementations;

import com.google.gson.reflect.TypeToken;
import com.yanovych.entities.Child;
import com.yanovych.entities.Room;
import com.yanovych.entities.Toy;
import com.yanovych.helpers.ObjectFileReader;
import com.yanovych.helpers.ObjectFileWriter;
import com.yanovych.repository.interfaces.RoomRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class RoomFromFileRepository implements RoomRepository {
    private final ObjectFileReader<Room> reader;
    private final ObjectFileWriter<Room> writer;
    private List<Room> rooms;
    private static RoomFromFileRepository instance = null;

    private RoomFromFileRepository() {
        this.reader = new ObjectFileReader<>();
        this.writer = new ObjectFileWriter<>();
        this.rooms = this.getAllRooms();
    }

    public static RoomFromFileRepository getInstance() {
        if (instance == null) {
            instance = new RoomFromFileRepository();
        }
        return instance;
    }

    @Override
    public Room getRoomById(Long id) {
        return this.getAllRooms().stream()
                .filter(room -> Objects.equals(room.getId(), id)).findAny().orElse(null);
    }

    @Override
    public List<Room> getAllRooms() {
        return reader.readListOfObjects("rooms.json", new TypeToken<ArrayList<Room>>(){}.getType());
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
        writer.writeListOfObjects("rooms.json", this.rooms, false);
    }

    @Override
    public void updateRoom(Room room) {
        for (int i = 0; i < this.rooms.size(); i++) {
            if (room.getId().equals(this.rooms.get(i).getId())) {
                this.rooms.set(i, room);
                writer.writeListOfObjects("rooms.json", this.rooms, false);
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

    @Override
    public void removeChildFromRoom(Child child, Room room) {
        List<Child> childrenInRoom = room.getChildrenInRoom();
        childrenInRoom.removeIf(childInRoom -> Objects.equals(childInRoom.getId(), child.getId()));
        room.setChildrenInRoom(childrenInRoom);
        this.updateRoom(room);
    }

    @Override
    public void addToyToRoom(Toy toy, Room room) {
        List<Toy> toysInRoom = room.getToysInRoom();
        if (toysInRoom == null) {
           toysInRoom = new ArrayList<>();
        }
        toysInRoom.add(toy);
        room.setBudget(room.getBudget() - toy.getPrice());
        room.setToysInRoom(toysInRoom);
        this.updateRoom(room);
    }

    @Override
    public void removeToyFromRoom(Toy toy, Room room) {
        List<Toy> toysInRoom = room.getToysInRoom();
        room.setBudget(room.getBudget() + toy.getPrice());
        toysInRoom.removeIf(toyInRoom -> Objects.equals(toyInRoom.getId(), toy.getId()));
        room.setToysInRoom(toysInRoom);
        this.updateRoom(room);
    }
}
