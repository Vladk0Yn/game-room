package com.yanovych.helpers.printers;

import com.yanovych.entities.Child;
import com.yanovych.entities.Room;
import com.yanovych.entities.Toy;
import lombok.Setter;

import java.util.List;

@Setter
public class RoomPrinter implements EntityPrinter {
    private List<Room> rooms;

    public RoomPrinter(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public void print() {
        for (Room room : rooms) {
            System.out.println("------------------------------------------------------------------------");
            System.out.print("ID: " + room.getId());
            System.out.println(" | Name: " + room.getName());
            System.out.print("Capacity: " + room.getCapacity());
            System.out.print(" | Budget: " + room.getBudget() + "$");
            System.out.println(" | Min/Max age: " + room.getMinimumChildAge() + "/" + room.getMaximumChildAge());
            if (room.getChildrenInRoom() != null && !room.getChildrenInRoom().isEmpty()) {
                System.out.print("Children in room { ");
                for(Child child : room.getChildrenInRoom()) {
                    System.out.print(child.getName() + "; ");
                }
                System.out.println("}");
            }
            if (room.getToysInRoom() != null && !room.getToysInRoom().isEmpty()) {
                System.out.print("Toys in room { ");
                for(Toy toy : room.getToysInRoom()) {
                    System.out.print(toy.getName() + "; ");
                }
                System.out.println("}");
            }
        }
        System.out.println("------------------------------------------------------------------------");
    }
}
