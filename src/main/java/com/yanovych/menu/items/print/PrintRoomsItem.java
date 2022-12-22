package com.yanovych.menu.items.print;

import com.yanovych.entities.Child;
import com.yanovych.entities.Room;
import com.yanovych.entities.Toy;
import com.yanovych.menu.MenuItem;
import com.yanovych.services.implementations.RoomServiceImplementation;
import com.yanovych.services.interfaces.RoomService;

import javax.crypto.spec.PSource;
import java.util.List;

public class PrintRoomsItem implements MenuItem {
    private final RoomService roomService = RoomServiceImplementation.getInstance();
    @Override
    public void doAction() {
        List<Room> rooms = roomService.getAllRooms();
        for (Room room : rooms) {
            System.out.println("-----------------------------------");
            System.out.println("ID: " + room.getId());
            System.out.println("Name: " + room.getName());
            System.out.println("Capacity: " + room.getCapacity());
            System.out.println("Budget: " + room.getBudget());
            System.out.println("Minimum age: " + room.getMinimumChildAge());
            System.out.println("Maximum age: " + room.getMaximumChildAge());
            if (room.getChildrenInRoom() != null) {
                System.out.print("Children in room { ");
                for(Child child : room.getChildrenInRoom()) {
                    System.out.print(child.getName() + "; ");
                }
                System.out.println("}");
            }
            if (room.getToysInRoom() != null) {
                System.out.print("Toys in room { ");
                for(Toy toy : room.getToysInRoom()) {
                    System.out.print(toy.getName() + "; ");
                }
                System.out.println("}");
            }
        }
        System.out.println("-----------------------------------");
    }
}
