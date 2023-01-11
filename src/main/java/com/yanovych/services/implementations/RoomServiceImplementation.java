package com.yanovych.services.implementations;

import com.yanovych.entities.Child;
import com.yanovych.entities.Room;
import com.yanovych.entities.Toy;
import com.yanovych.repository.implementations.ChildFromFileRepository;
import com.yanovych.repository.implementations.RoomFromFileRepository;
import com.yanovych.repository.implementations.ToyFromFileRepository;
import com.yanovych.repository.interfaces.ChildRepository;
import com.yanovych.repository.interfaces.RoomRepository;
import com.yanovych.repository.interfaces.ToyRepository;
import com.yanovych.services.interfaces.RoomService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class RoomServiceImplementation implements RoomService {

    private static RoomServiceImplementation instance = null;
    private final RoomRepository roomFileRepository = RoomFromFileRepository.getInstance();
    private final ChildRepository childFileRepository = ChildFromFileRepository.getInstance();
    private final ToyRepository toyFileRepository = ToyFromFileRepository.getInstance();

    private RoomServiceImplementation() {
    }

    public static RoomServiceImplementation getInstance() {
        if (instance == null) {
            instance = new RoomServiceImplementation();
        }
        return instance;
    }

    @Override
    public Room getRoomById(Long id) {
        Room room = this.roomFileRepository.getRoomById(id);
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
        this.roomFileRepository.addRoom(room);
        log.info("IN create - room: {} successfully created", room.getName());
    }

    @Override
    public List<Room> getAllRooms() {
        List<Room> rooms = this.roomFileRepository.getAllRooms();
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
        this.childFileRepository.addChildToRoom(child, room);
        this.roomFileRepository.addChildToRoom(child, room);
        log.info("IN addChildToRoom - child: {} successfully added - room: {}", child.getName(), room.getName());
    }

    @Override
    public void removeChildFromRoom(Child child, Room room) {
        this.roomFileRepository.removeChildFromRoom(child, room);
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
        this.toyFileRepository.addToyToRoom(toy, room);
        this.roomFileRepository.addToyToRoom(toy, room);
        log.info("IN addToyToRoom - toy: {} successfully added - room: {}", toy.getName(), room.getName());
    }

    @Override
    public void removeToyFromRoom(Toy toy, Room room) {
        this.roomFileRepository.removeToyFromRoom(toy, room);
        log.info("IN removeToyFromRoom - toy: {} successfully removed - room {}", toy.getName(), room.getName());
    }

    @Override
    public List<Room> getAvailableRoomsForAge(Integer age) {
        return this.getAllRooms().stream()
                .filter(room -> room.getMinimumChildAge() <= age && room.getMaximumChildAge() >= age)
                .toList();
    }

    @Override
    public List<Room> getAvailableRoomsForToy(Toy toy) {
        List<Room> availableRoomsByBudget = this.getAvailableRoomsForAge(toy.getMinimumAge()).stream()
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
