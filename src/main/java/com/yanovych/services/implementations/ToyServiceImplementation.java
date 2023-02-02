package com.yanovych.services.implementations;

import com.yanovych.entities.Room;
import com.yanovych.entities.Toy;
import com.yanovych.helpers.PropertiesManager;
import com.yanovych.repositories.implementations.RoomFromDbRepository;
import com.yanovych.repositories.implementations.RoomFromFileRepository;
import com.yanovych.repositories.implementations.ToyFromDbRepository;
import com.yanovych.repositories.implementations.ToyFromFileRepository;
import com.yanovych.repositories.interfaces.RoomRepository;
import com.yanovych.repositories.interfaces.ToyRepository;
import com.yanovych.services.interfaces.ToyService;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

@Slf4j
public class ToyServiceImplementation implements ToyService {
    private static ToyServiceImplementation instance = null;
    private ToyRepository toyRepository = null;
    private RoomRepository roomRepository = null;
    private ToyServiceImplementation() {
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
                toyRepository = ToyFromFileRepository.getInstance();
                roomRepository = RoomFromFileRepository.getInstance();
            }
            case "db" -> {
                toyRepository = ToyFromDbRepository.getInstance();
                roomRepository = RoomFromDbRepository.getInstance();
            }
            default -> {
                log.error("Error at reading data source, default data source is file");
                toyRepository = ToyFromFileRepository.getInstance();
                roomRepository = RoomFromFileRepository.getInstance();
            }
        }
    }

    public static ToyServiceImplementation getInstance() {
        if (instance == null) {
            instance = new ToyServiceImplementation();
        }
        return instance;
    }

    @Override
    public Toy getToyById(Long id) {
        Toy toy = this.toyRepository.getToyById(id);
        if (toy == null) {
            log.error("IN getToyById - no toy with id: {}", id);
            return null;
        }
        log.info("IN getToyById - toy: {} successfully found", toy.getName());
        return toy;
    }

    @Override
    public void createToy(Toy toy) {
        toyRepository.addToy(toy);
        log.info("IN createToy - toy: {} successfully created", toy.getName());
    }

    @Override
    public List<Toy> getAllToys() {
        List<Toy> toys = toyRepository.getAllToys();
        log.info("IN getAllToys - toys: {} successfully received", toys.size());
        return toys;
    }

    @Override
    public List<Toy> sortToysInRoomByType(Room room) {
        return room.getToysInRoom().stream()
                .sorted(Comparator.comparing(Toy::getType))
                .toList();
    }

    @Override
    public List<Toy> findToysInRoomByDiapasonOfPrice(Room room, Double priceMin, Double priceMax) {
        return room.getToysInRoom().stream()
                .filter(toy -> toy.getPrice() >= priceMin && toy.getPrice() <= priceMax)
                .toList();
    }

    @Override
    public void deleteToy(Toy toy) {
        if (toy.getToyRoomId() != null) {
            Room toyRoom = this.roomRepository.getRoomById(toy.getToyRoomId());
            this.roomRepository.removeToyFromRoom(toy, toyRoom);
        }
        this.toyRepository.deleteToy(toy);
    }
}
