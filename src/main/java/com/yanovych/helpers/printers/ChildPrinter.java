package com.yanovych.helpers.printers;

import com.yanovych.entities.Child;
import com.yanovych.services.implementations.RoomServiceImplementation;
import com.yanovych.services.interfaces.RoomService;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Setter
public class ChildPrinter implements EntityPrinter {
    private List<Child> children;
    private RoomService roomService = RoomServiceImplementation.getInstance();

    public ChildPrinter(List<Child> children) {
        this.children = children;
    }

    @Override
    public void print() {
        for (Child child : children) {
            System.out.println("------------------------------------------------------------------------");
            System.out.print("ID: " + child.getId());
            System.out.println(" | Name: " + child.getName());
            System.out.print("Age: " + child.getAge());
            System.out.print(" | Sex: " + child.getSex().toString().toLowerCase());
            if (child.getRoomId() != null) {
                System.out.println(" | Room: " + roomService.getRoomById(child.getRoomId()).getName());
            } else {
                System.out.println(" | No room");
            }
        }
        System.out.println("------------------------------------------------------------------------");
    }
}
