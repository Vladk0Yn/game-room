package com.yanovych.menu.items;

import com.yanovych.entities.Room;
import com.yanovych.entities.Toy;
import com.yanovych.helpers.printers.EntityPrinter;
import com.yanovych.helpers.printers.RoomPrinter;
import com.yanovych.helpers.printers.ToyPrinter;
import com.yanovych.menu.MenuItem;
import com.yanovych.services.implementations.RoomServiceImplementation;
import com.yanovych.services.implementations.ToyServiceImplementation;
import com.yanovych.services.interfaces.RoomService;
import com.yanovych.services.interfaces.ToyService;

import java.util.List;
import java.util.Scanner;

public class SortToysInRoomItem implements MenuItem {
    private final ToyService toyService = ToyServiceImplementation.getInstance();
    private final RoomService roomService = RoomServiceImplementation.getInstance();
    @Override
    public void doAction() {
        List<Toy> toys = toyService.sortToysInRoomByType(getRoomsFromListByUser());
        printToys(toys);
    }

    private void printToys(List<Toy> toys) {
        EntityPrinter printer = new ToyPrinter(toys);
        printer.print();
    }
    private Room getRoomsFromListByUser() {
        Room room = null;
        while (room == null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("All rooms: ");
            EntityPrinter printer = new RoomPrinter(this.roomService.getAllRooms());
            printer.print();
            System.out.print("Enter room id in which you want sort toys  -> ");
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
