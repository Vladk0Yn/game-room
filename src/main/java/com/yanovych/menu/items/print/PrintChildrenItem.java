package com.yanovych.menu.items.print;

import com.yanovych.entities.Child;
import com.yanovych.helpers.printers.ChildPrinter;
import com.yanovych.helpers.printers.EntityPrinter;
import com.yanovych.menu.MenuItem;
import com.yanovych.services.implementations.ChildServiceImplementation;
import com.yanovych.services.interfaces.ChildService;

import java.util.List;

public class PrintChildrenItem implements MenuItem {

    private final ChildService childService = ChildServiceImplementation.getInstance();
    @Override
    public void doAction() {
        List<Child> children = childService.getAllChildren();
        EntityPrinter printer = new ChildPrinter(children);
        printer.print();
    }
}
