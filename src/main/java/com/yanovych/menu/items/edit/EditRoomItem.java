package com.yanovych.menu.items.edit;

import com.yanovych.entities.Room;
import com.yanovych.helpers.printers.EntityPrinter;
import com.yanovych.helpers.printers.RoomPrinter;
import com.yanovych.menu.MenuItem;
import com.yanovych.services.implementations.RoomServiceImplementation;
import com.yanovych.services.interfaces.RoomService;

import java.util.Scanner;

public class EditRoomItem implements MenuItem {
    private final RoomService roomService = RoomServiceImplementation.getInstance();
    @Override
    public void doAction() {
        this.roomService.updateRoom(editRoomFromKeyboardInput(getRoomsFromListByUser()));
    }

    private Room getRoomsFromListByUser() {
        Room room = null;
        while (room == null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("All rooms: ");
            EntityPrinter printer = new RoomPrinter(this.roomService.getAllRooms());
            printer.print();
            System.out.print("Enter room id which you want edit  -> ");
            Long id = scanner.nextLong();
            scanner.nextLine();
            room = this.roomService.getRoomById(id);
            if (room == null) {
                System.out.println("No room with id " + id);
            }
        }
        return room;
    }

    private Room editRoomFromKeyboardInput(Room room) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Edit room name (current: "  + room.getName() + ") -> ");
        String name = scanner.nextLine();
        System.out.print("Edit capacity (toys) of room (current: "  + room.getCapacity() + ") -> ");
        Integer capacity = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Edit room budget (current: "  + room.getBudget() + ") -> ");
        Double budget = scanner.nextDouble();
        System.out.print("Edit minimum child age (current: "  + room.getMinimumChildAge() + ") -> ");
        Integer minimumChildAge = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Edit maximum child age (current: "  + room.getMaximumChildAge() + ") -> ");
        Integer maximumChildAge = scanner.nextInt();
        scanner.nextLine();

        room.setName(name);
        room.setCapacity(capacity);
        room.setBudget(budget);
        room.setMinimumChildAge(minimumChildAge);
        room.setMaximumChildAge(maximumChildAge);

        return room;
    }
}
