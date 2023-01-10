package com.yanovych.menu.items.print;

import com.yanovych.entities.Child;
import com.yanovych.entities.Room;
import com.yanovych.helpers.printers.ChildPrinter;
import com.yanovych.helpers.printers.EntityPrinter;
import com.yanovych.menu.MenuItem;
import com.yanovych.services.implementations.ChildServiceImplementation;
import com.yanovych.services.implementations.RoomServiceImplementation;
import com.yanovych.services.interfaces.ChildService;
import com.yanovych.services.interfaces.RoomService;

import java.util.List;
import java.util.Objects;

public class PrintChildrenItem implements MenuItem {

    private final ChildService childService = ChildServiceImplementation.getInstance();
    private final RoomService roomService = RoomServiceImplementation.getInstance();
    @Override
    public void doAction() {
        List<Child> children = childService.getAllChildren();
        EntityPrinter printer = new ChildPrinter(children);
        printer.print();
    }
}
