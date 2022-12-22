package com.yanovych.menu.items.print;

import com.yanovych.entities.Child;
import com.yanovych.entities.Room;
import com.yanovych.menu.MenuItem;
import com.yanovych.services.implementations.ChildServiceImplementation;
import com.yanovych.services.implementations.RoomServiceImplementation;
import com.yanovych.services.interfaces.ChildService;
import com.yanovych.services.interfaces.RoomService;

import java.util.List;
import java.util.Objects;

public class PrintChildrenItem implements MenuItem {

    private final ChildService childService = ChildServiceImplementation.getInstance();
    private final RoomService roomService = RoomServiceImplementation.getInstance();
    @Override
    public void doAction() {
        List<Child> children = childService.getAllChildren();
        List<Room> rooms = roomService.getAllRooms();
        for (Child child : children) {
            System.out.println("-----------------------------------");
            System.out.println("ID: " + child.getId());
            System.out.println("Name: " + child.getName());
            System.out.println("Age: " + child.getAge());
            System.out.println("Sex: " + child.getSex().toString().toLowerCase());
            if (child.getRoomId() != null) {
                rooms.stream()
                        .filter(room -> Objects.equals(room.getId(), child.getRoomId()))
                        .findFirst().ifPresent(childRoom -> System.out.println("Room: " + childRoom.getName()));
            }
        }
        System.out.println("-----------------------------------");
    }
}
