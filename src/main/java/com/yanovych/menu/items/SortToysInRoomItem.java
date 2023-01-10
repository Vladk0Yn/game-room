package com.yanovych.menu.items;

import com.yanovych.entities.Child;
import com.yanovych.entities.Room;
import com.yanovych.entities.Toy;
import com.yanovych.helpers.printers.EntityPrinter;
import com.yanovych.helpers.printers.RoomPrinter;
import com.yanovych.helpers.printers.ToyPrinter;
import com.yanovych.menu.MenuItem;
import com.yanovych.menu.items.print.PrintRoomsItem;
import com.yanovych.menu.items.print.PrintToysItem;
import com.yanovych.services.implementations.RoomServiceImplementation;
import com.yanovych.services.implementations.ToyServiceImplementation;
import com.yanovych.services.interfaces.RoomService;
import com.yanovych.services.interfaces.ToyService;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class SortToysInRoomItem implements MenuItem {
    private final ToyService toyService = ToyServiceImplementation.getInstance();
    private final RoomService roomService = RoomServiceImplementation.getInstance();
    @Override
    public void doAction() {
        List<Toy> toys = toyService.sortToysInRoomByType(chooseRoomFromKeyboardInput());
        printToys(toys);
    }

    private void printToys(List<Toy> toys) {
        EntityPrinter printer = new ToyPrinter(toys);
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
