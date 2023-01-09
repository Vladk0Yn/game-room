package com.yanovych.menu.items.add;

import com.yanovych.entities.Child;
import com.yanovych.entities.Room;
import com.yanovych.menu.MenuItem;
import com.yanovych.services.implementations.ChildServiceImplementation;
import com.yanovych.services.implementations.RoomServiceImplementation;
import com.yanovych.services.interfaces.ChildService;
import com.yanovych.services.interfaces.RoomService;

import java.util.Scanner;

public class AddChildToRoomItem implements MenuItem {
    private final ChildService childService = ChildServiceImplementation.getInstance();
    private final RoomService roomService = RoomServiceImplementation.getInstance();
    @Override
    public void doAction() {
        Child child = getChildFromListByUser();
        Room room = getRoomForChildFromListByUser(child);
        this.roomService.addChildToRoom(child, room);
    }

    private Child getChildFromListByUser() {
        Child child = null;
        while (child == null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Children without room (you can add another children too): ");
            System.out.println(this.childService.getChildrenWithoutRoom());
            System.out.print("Enter child id which you want add to room -> ");
            Long id = scanner.nextLong();
            scanner.nextLine();
            child = this.childService.getChildById(id);
            if (child == null) {
                System.out.println("No child with id " + id);
            }
        }
        return child;
    }

    private Room getRoomForChildFromListByUser(Child child) {
        Room room = null;
        while (room == null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Available rooms for this child: ");
            System.out.println(this.roomService.getAvailableRoomsForAge(child.getAge()));
            System.out.print("Enter room id  -> ");
            Long id = scanner.nextLong();
            scanner.nextLine();
            room = this.roomService.getRoomForChildByRoomId(id, child);
            if (room == null) {
                System.out.println("No room with id " + id);
            }
        }
        return room;
    }
}
