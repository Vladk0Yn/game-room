package com.yanovych.menu.items.print;

import com.yanovych.entities.Child;
import com.yanovych.entities.Room;
import com.yanovych.entities.Toy;
import com.yanovych.helpers.printers.EntityPrinter;
import com.yanovych.helpers.printers.RoomPrinter;
import com.yanovych.menu.MenuItem;
import com.yanovych.services.implementations.RoomServiceImplementation;
import com.yanovych.services.interfaces.RoomService;

import java.util.List;

public class PrintRoomsItem implements MenuItem {
    private final RoomService roomService = RoomServiceImplementation.getInstance();
    @Override
    public void doAction() {
        List<Room> rooms = roomService.getAllRooms();
        EntityPrinter printer = new RoomPrinter(rooms);
        printer.print();
    }
}
