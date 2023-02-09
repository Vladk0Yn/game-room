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

public class FindToysInRoomItem implements MenuItem {
    private final ToyService toyService = ToyServiceImplementation.getInstance();
    private final RoomService roomService = RoomServiceImplementation.getInstance();
    @Override
    public void doAction() {
        Room room = getRoomsFromListByUser();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum price of toy -> ");
        Double minPrice = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter maximum price of toy -> ");
        Double maxPrice = scanner.nextDouble();
        scanner.nextLine();
        List<Toy> searchedToys = this.toyService.findToysInRoomByDiapasonOfPrice(
                room, minPrice, maxPrice);
        System.out.println("Search result:");
        if (searchedToys.isEmpty()) {
            System.out.println("No toys in room with your parameters");
        }
        EntityPrinter printer = new ToyPrinter(searchedToys);
        printer.print();
    }

    private Room getRoomsFromListByUser() {
        Room room = null;
        while (room == null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("All rooms: ");
            EntityPrinter printer = new RoomPrinter(this.roomService.getAllRooms());
            printer.print();
            System.out.print("Enter room id in which you want find toys  -> ");
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
