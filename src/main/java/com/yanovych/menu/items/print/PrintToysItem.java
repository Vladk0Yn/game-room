package com.yanovych.menu.items.print;

import com.yanovych.entities.Room;
import com.yanovych.entities.Toy;
import com.yanovych.menu.MenuItem;
import com.yanovych.services.implementations.RoomServiceImplementation;
import com.yanovych.services.implementations.ToyServiceImplementation;
import com.yanovych.services.interfaces.RoomService;
import com.yanovych.services.interfaces.ToyService;

import java.util.List;
import java.util.Objects;

public class PrintToysItem implements MenuItem {

    private final ToyService toyService = ToyServiceImplementation.getInstance();
    private final RoomService roomService = RoomServiceImplementation.getInstance();
    @Override
    public void doAction() {
        List<Toy> toys = toyService.getAllToys();
        List<Room> rooms = roomService.getAllRooms();
        for(Toy toy : toys) {
            System.out.println("-----------------------------------");
            System.out.println("ID: " + toy.getId());
            System.out.println("Name: " + toy.getName());
            System.out.println("Minimum age: " + toy.getMinimumAge());
            System.out.println("Price: " + toy.getPrice());
            System.out.println("Type: " + toy.getType().toString());
            System.out.println("Size: " + toy.getSize().toString());
            System.out.println("Color: " + toy.getColor().toString());
            System.out.println("Material: " + toy.getMaterial().toString());
            if (toy.getToyRoomId() != null) {
                rooms.stream()
                        .filter(room -> Objects.equals(room.getId(), toy.getToyRoomId()))
                        .findFirst().ifPresent(toyRoom -> System.out.println("Room: " + toyRoom.getName()));
            }
        }
        System.out.println("-----------------------------------");
    }
}
