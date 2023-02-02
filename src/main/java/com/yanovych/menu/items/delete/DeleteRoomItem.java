package com.yanovych.menu.items.delete;

import com.yanovych.entities.Room;
import com.yanovych.entities.Toy;
import com.yanovych.helpers.printers.EntityPrinter;
import com.yanovych.helpers.printers.RoomPrinter;
import com.yanovych.menu.MenuItem;
import com.yanovych.services.implementations.RoomServiceImplementation;
import com.yanovych.services.interfaces.RoomService;

import java.util.Scanner;

public class DeleteRoomItem implements MenuItem {
    private final RoomService roomService = RoomServiceImplementation.getInstance();
    @Override
    public void doAction() {
        this.roomService.deleteRoom(getRoomsFromListByUser());
    }

    private Room getRoomsFromListByUser() {
        Room room = null;
        while (room == null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("All rooms: ");
            EntityPrinter printer = new RoomPrinter(this.roomService.getAllRooms());
            printer.print();
            System.out.print("Enter room id which you want delete  -> ");
            Long id = scanner.nextLong();
            scanner.nextLine();
            room = this.roomService.getRoomById(id);
            if (room == null) {
                System.out.println("No room with id " + id);
            }
        }
        return room;
    }
}
