package com.yanovych.menu.items.add;

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

import java.util.Scanner;

public class AddToyToRoom implements MenuItem {
    private final ToyService toyService = ToyServiceImplementation.getInstance();
    private final RoomService roomService = RoomServiceImplementation.getInstance();
    @Override
    public void doAction() {
        Toy toy = getToyFromListByUser();
        Room room = getRoomForToyFromListByUser(toy);
        this.roomService.addToyToRoom(toy, room);
    }

    private Toy getToyFromListByUser() {
        Toy toy = null;
        while (toy == null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("All toys: ");
            EntityPrinter printer = new ToyPrinter(this.toyService.getAllToys());
            printer.print();
            System.out.print("Enter toy id which you want add to room -> ");
            Long id = scanner.nextLong();
            scanner.nextLine();
            toy  = this.toyService.getToyById(id);
            if (toy == null) {
                System.out.println("No toy with id " + id);
            }
        }
        return toy;
    }

    private Room getRoomForToyFromListByUser(Toy toy) {
        Room room = null;
        while (room == null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Available rooms for this toy: ");
            EntityPrinter printer = new RoomPrinter(this.roomService.getAvailableRoomsForAge(toy.getMinimumAge()));
            printer.print();
            System.out.print("Enter room id  -> ");
            Long id = scanner.nextLong();
            scanner.nextLine();
            room = this.roomService.getRoomForToyByRoomId(id, toy);
            if (room == null) {
                System.out.println("No room with id " + id);
            }
        }
        return room;
    }
}
