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
    public void createRoom(Room room) {
        roomFileRepository.addRoom(room);
        log.info("IN create - room: {} successfully created", room.getName());
    }

    @Override
    public List<Room> getAllRooms() {
        List<Room> rooms = roomFileRepository.getAllRooms();
        log.info("IN getAll - rooms: {} successfully received", rooms.size());
        return rooms;
    }

    @Override
    public void addChildToRoom(Child child, Room room) {
        roomFileRepository.addChildToRoom(child, room);
        childFileRepository.addChildToRoom(child, room);
        log.info("IN addChild - child: {} successfully added - room: {}", child.getName(), room.getName());
    }
}
