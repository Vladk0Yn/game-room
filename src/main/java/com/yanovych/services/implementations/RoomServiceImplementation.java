package com.yanovych.services.implementations;

import com.yanovych.entities.Child;
import com.yanovych.entities.Room;
import com.yanovych.repository.implementations.ChildFromFileRepository;
import com.yanovych.repository.implementations.RoomFromFileRepository;
import com.yanovych.repository.interfaces.ChildRepository;
import com.yanovych.repository.interfaces.RoomRepository;
import com.yanovych.services.interfaces.RoomService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

@Slf4j
public class RoomServiceImplementation implements RoomService {

    private static RoomServiceImplementation instance = null;
    private final RoomRepository roomFileRepository = RoomFromFileRepository.getInstance();
    private final ChildRepository childFileRepository = ChildFromFileRepository.getInstance();

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
        log.info("IN getRoomById - room: {} successfully found", room.getName());
        return room;
    }

    @Override
    public Room getRoomForChildAgeById(Long id, Child child) {
        Room roomForChildAge = this.getAvailableRoomsForChildAge(child)
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
        if (child.getRoomId() != null) {
            this.removeChildFromRoom(child, getRoomById(child.getRoomId()));
        }
        this.roomFileRepository.addChildToRoom(child, room);
        this.childFileRepository.addChildToRoom(child, room);
        log.info("IN addChild - child: {} successfully added - room: {}", child.getName(), room.getName());
    }

    @Override
    public void removeChildFromRoom(Child child, Room room) {
        this.roomFileRepository.removeChildFromRoom(child, room);
        log.info("IN removeChild - child: {} successfully removed - room {}", child.getName(), room.getName());
    }

    @Override
    public List<Room> getAvailableRoomsForChildAge(Child child) {
        return this.getAllRooms().stream()
                .filter(room -> room.getMinimumChildAge() <= child.getAge() && room.getMaximumChildAge() >= child.getAge())
                .toList();
    }
}
