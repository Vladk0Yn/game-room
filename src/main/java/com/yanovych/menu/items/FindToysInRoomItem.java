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
        Room room = chooseRoomFromKeyboardInput();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum price of toy -> ");
        Double minPrice = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter maximum price of toy -> ");
        Double maxPrice = scanner.nextDouble();
        scanner.nextLine();
        List<Toy> searchedToys = this.toyService.findToysInRoomByDiapasonOfPrice(
                room, minPrice, maxPrice);
        EntityPrinter printer = new ToyPrinter(searchedToys);
        printer.print();
    }

    private Room chooseRoomFromKeyboardInput() {
        Scanner scanner = new Scanner(System.in);
        EntityPrinter printer = new RoomPrinter(roomService.getAllRooms());
        printer.print();
        System.out.print("Enter room id -> ");
        Long id = scanner.nextLong();
        return roomService.getRoomById(id);
    }
}
