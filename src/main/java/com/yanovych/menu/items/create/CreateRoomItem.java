package com.yanovych.menu.items.create;

import com.yanovych.entities.Room;
import com.yanovych.menu.MenuItem;
import com.yanovych.services.implementations.RoomServiceImplementation;
import com.yanovych.services.interfaces.RoomService;

import java.util.Scanner;

public class CreateRoomItem implements MenuItem {

    private final RoomService roomService = RoomServiceImplementation.getInstance();
    @Override
    public void doAction() {
        roomService.createRoom(getRoomFromKeyboardInput());
    }

    private Room getRoomFromKeyboardInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter room name -> ");
        String name = scanner.nextLine();
        System.out.print("Enter capacity of room -> ");
        Integer capacity = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter room budget -> ");
        Double budget = scanner.nextDouble();
        System.out.print("Enter minimum child age -> ");
        Integer minimumChildAge = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter maximum child age -> ");
        Integer maximumChildAge = scanner.nextInt();
        scanner.nextLine();

        return new Room(name, capacity, budget, minimumChildAge, maximumChildAge);
    }
}
