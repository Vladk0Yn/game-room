package com.yanovych.services.implementations;

import com.yanovych.entities.Child;
import com.yanovych.entities.Room;
import com.yanovych.entities.Toy;
import com.yanovych.helpers.PropertiesManager;
import com.yanovych.repositories.implementations.*;
import com.yanovych.repositories.interfaces.ChildRepository;
import com.yanovych.repositories.interfaces.RoomRepository;
import com.yanovych.repositories.interfaces.ToyRepository;
import com.yanovych.services.interfaces.RoomService;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

@Slf4j
public class RoomServiceImplementation implements RoomService {

    private static RoomServiceImplementation instance = null;
    private final RoomRepository roomRepository;
    private final ChildRepository childRepository;
    private final ToyRepository toyRepository;

    private RoomServiceImplementation() {
        String dataSource;
        try {
            Properties properties = PropertiesManager.getProperties("project.properties");
            dataSource = properties.getProperty("datasource");
        } catch (IOException e) {
            log.error("Properties file not found");
            throw new RuntimeException(e);
        }
        switch (dataSource) {
            case "file" -> {
                roomRepository = RoomFromFileRepository.getInstance();
                childRepository = ChildFromFileRepository.getInstance();
                toyRepository = ToyFromFileRepository.getInstance();
            }
            case "db" -> {
                roomRepository = RoomFromDbRepository.getInstance();
                childRepository = ChildFromDbRepository.getInstance();
                toyRepository = ToyFromDbRepository.getInstance();
            }
            default -> {
                log.error("Error at reading environment variable DATA_SOURCE, default data source is file");
                childRepository = ChildFromFileRepository.getInstance();
                roomRepository = RoomFromFileRepository.getInstance();
                toyRepository = ToyFromFileRepository.getInstance();
            }
        }
    }

    public static RoomServiceImplementation getInstance() {
        if (instance == null) {
            instance = new RoomServiceImplementation();
        }
        return instance;
    }

    @Override
    public Room getRoomById(Long id) {
        Room room = this.roomRepository.getRoomById(id);
        if (room == null) {
            log.error("IN getRoomById - no room with id: {}", id);
            return null;
        }
        return room;
    }

    @Override
    public Room getRoomForChildByRoomId(Long id, Child child) {
        Room roomForChildAge = this.getAvailableRoomsForAge(child.getAge())
                .stream()
                .filter(room -> Objects.equals(room.getId(), id))
                .findAny().orElse(null);
        if (roomForChildAge == null) {
            log.error("IN getRoomForChildAgeById - no room with id: {}", id);
            return null;
        }
        log.info("IN getRoomForChildAgeById - room: {} successfully found", roomForChildAge.getName());
        return roomForChildAge;
    }

    @Override
    public Room getRoomForToyByRoomId(Long id, Toy toy) {
        Room roomForToy = this.getAvailableRoomsForToy(toy)
                .stream()
                .filter(room -> Objects.equals(room.getId(), id))
                .findAny().orElse(null);
        if (roomForToy == null) {
            log.error("IN getRoomForToyByRoomId - no room with id: {}", id);
            return null;
        }
        log.info("IN getRoomForToyByRoomId - room: {} successfully found", roomForToy.getName());
        return roomForToy;
    }

    @Override
    public void createRoom(Room room) {
        this.roomRepository.addRoom(room);
        log.info("IN create - room: {} successfully created", room.getName());
    }

    @Override
    public List<Room> getAllRooms() {
        List<Room> rooms = this.roomRepository.getAllRooms();
        log.info("IN getAll - rooms: {} successfully received", rooms.size());
        return rooms;
    }

    @Override
    public void addChildToRoom(Child child, Room room) {
        if (room.getToysInRoom() != null && room.getChildrenInRoom().contains(child)) {
            log.error("IN addChildToRoom - child: {} already in room", child.getName());
            return;
        }
        if (child.getRoomId() != null) {
            this.removeChildFromRoom(child, getRoomById(child.getRoomId()));
        }
        this.childRepository.addChildToRoom(child, room);
        this.roomRepository.addChildToRoom(child, room);
        log.info("IN addChildToRoom - child: {} successfully added - room: {}", child.getName(), room.getName());
    }

    @Override
    public void removeChildFromRoom(Child child, Room room) {
        this.roomRepository.removeChildFromRoom(child, room);
        log.info("IN removeChildToRoom - child: {} successfully removed - room {}", child.getName(), room.getName());
    }

    @Override
    public void addToyToRoom(Toy toy, Room room) {
        if (room.getToysInRoom() != null && room.getToysInRoom().contains(toy)) {
            log.error("IN addToyToRoom - toy: {} already in room", toy.getName());
            return;
        }
        if (room.getBudget() < toy.getPrice()) {
            log.error("IN addToyToRoom - room: {} budget is not enough for - toy: {}", room.getName(), toy.getName());
            return;
        }
        if (room.getToysInRoom() != null && !room.getToysInRoom().isEmpty() && room.getCapacity() < room.getToysInRoom().size() + 1) {
            log.error("IN addToyToRoom - room: {} is full of toys", room.getName());
            return;
        }
        if (toy.getToyRoomId() != null) {
            this.removeToyFromRoom(toy, getRoomById(toy.getToyRoomId()));
        }
        this.toyRepository.addToyToRoom(toy, room);
        this.roomRepository.addToyToRoom(toy, room);
        log.info("IN addToyToRoom - toy: {} successfully added - room: {}", toy.getName(), room.getName());
    }

    @Override
    public void removeToyFromRoom(Toy toy, Room room) {
        this.roomRepository.removeToyFromRoom(toy, room);
        log.info("IN removeToyFromRoom - toy: {} successfully removed - room {}", toy.getName(), room.getName());
    }

    @Override
    public List<Room> getAvailableRoomsForAge(Integer age) {
        return this.getAllRooms().stream()
                .filter(room -> room.getMinimumChildAge() >= age && room.getMaximumChildAge() >= age)
                .toList();
    }

    @Override
    public List<Room> getAvailableRoomsForToy(Toy toy) {
        List<Room> availableRoomByAge = this.getAllRooms().stream()
                .filter(room -> room.getMinimumChildAge() <= toy.getMinimumAge())
                .toList();
        List<Room> availableRoomsByBudget = availableRoomByAge.stream()
                .filter(room -> room.getBudget() >= toy.getPrice())
                .toList();
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : availableRoomsByBudget) {
            if (room.getToysInRoom() != null && !room.getToysInRoom().isEmpty()) {
                if (room.getCapacity() >= room.getToysInRoom().size() + 1) {
                    availableRooms.add(room);
                }
            } else {
                availableRooms.add(room);
            }
        }
        log.info("IN getAvailableRoomsForToy - {} rooms is available", availableRooms.size());
        return availableRooms;
    }
}
