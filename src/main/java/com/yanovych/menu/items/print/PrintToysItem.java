package com.yanovych.menu.items.print;

import com.yanovych.entities.Toy;
import com.yanovych.helpers.printers.EntityPrinter;
import com.yanovych.helpers.printers.ToyPrinter;
import com.yanovych.menu.MenuItem;
import com.yanovych.services.implementations.ToyServiceImplementation;
import com.yanovych.services.interfaces.ToyService;

import java.util.List;

public class PrintToysItem implements MenuItem {

    private final ToyService toyService = ToyServiceImplementation.getInstance();

    @Override
    public void doAction() {
        List<Toy> toys = toyService.getAllToys();
        EntityPrinter printer = new ToyPrinter(toys);
        printer.print();
    }
}
