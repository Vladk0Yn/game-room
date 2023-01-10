package com.yanovych.helpers.printers;

import com.yanovych.entities.Toy;
import com.yanovych.services.implementations.RoomServiceImplementation;
import com.yanovych.services.interfaces.RoomService;
import lombok.Setter;

import java.util.List;

@Setter
public class ToyPrinter implements EntityPrinter {
    private List<Toy> toys;
    private RoomService roomService = RoomServiceImplementation.getInstance();

    public ToyPrinter(List<Toy> toys) {
        this.toys = toys;
    }

    @Override
    public void print() {
        for(Toy toy : toys) {
            System.out.println("------------------------------------------------------------------------");
            System.out.print("ID: " + toy.getId());
            System.out.println(" | Name: " + toy.getName());
            System.out.print("Minimum age: " + toy.getMinimumAge());
            System.out.print(" | Price: " + toy.getPrice() + "$");
            if (toy.getToyRoomId() != null) {
                System.out.println(" | Room: " + roomService.getRoomById(toy.getToyRoomId()).getName());
            } else {
                System.out.println(" | No room");
            }
            System.out.print("Type: " + toy.getType().toString());
            System.out.print(" | Size: " + toy.getSize().toString());
            System.out.print(" | Color: " + toy.getColor().toString());
            System.out.println(" | Material: " + toy.getMaterial().toString());
        }
        System.out.println("------------------------------------------------------------------------");
    }
}
