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
        log.info("IN getRoomById - room: {} successfully found", room.getName());
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
        Room roomForToyAge = this.getAvailableRoomsForAge(toy.getMinimumAge())
                .stream()
                .filter(room -> Objects.equals(room.getId(), id))
                .findAny().orElse(null);
        if (roomForToyAge == null) {
            log.error("IN getRoomForToyByRoomId - no room with id: {}", id);
            return null;
        }
        log.info("IN getRoomForToyByRoomId - room: {} successfully found", roomForToyAge.getName());
        return roomForToyAge;
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
        log.info("IN addChildToRoom - child: {} successfully added - room: {}", child.getName(), room.getName());
    }

    @Override
    public void removeChildFromRoom(Child child, Room room) {
        if (!room.getChildrenInRoom().contains(child)) {
            log.error("IN removeChildFromRoom - no child: {} in - room {}", child.getName(), room.getName());
            return;
        }
        this.roomFileRepository.removeChildFromRoom(child, room);
        log.info("IN removeChildToRoom - child: {} successfully removed - room {}", child.getName(), room.getName());
    }

    @Override
    public void addToyToRoom(Toy toy, Room room) {
        if (room.getBudget() < toy.getPrice()) {
            log.error("IN addToyToRoom - room: {} budget is not enough for - toy: {}", room.getName(), toy.getName());
            return;
        }
        if (room.getCapacity() > room.getChildrenInRoom().size() + 1) {
            log.error("IN addToyToRoom - room: {} is full of toys", room.getName());
            return;
        }
        if (toy.getToyRoomId() != null) {
            this.removeToyFromRoom(toy, getRoomById(toy.getToyRoomId()));
        }
        this.roomFileRepository.addToyToRoom(toy, room);
        this.toyFileRepository.addToyToRoom(toy, room);
        log.info("IN addToyToRoom - toy: {} successfully added - room: {}", toy.getName(), room.getName());
    }

    @Override
    public void removeToyFromRoom(Toy toy, Room room) {
        if (!room.getToysInRoom().contains(toy)) {
            log.error("IN removeToyFromRoom - no toy: {} in - room {}", toy.getName(), room.getName());
            return;
        }
        this.roomFileRepository.removeToyFromRoom(toy, room);
        log.info("IN removeToyFromRoom - toy: {} successfully removed - room {}", toy.getName(), room.getName());
    }

    @Override
    public List<Room> getAvailableRoomsForAge(Integer age) {
        return this.getAllRooms().stream()
                .filter(room -> room.getMinimumChildAge() <= age && room.getMaximumChildAge() >= age)
                .toList();
    }
}
