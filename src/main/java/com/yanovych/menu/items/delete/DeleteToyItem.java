package com.yanovych.menu.items.delete;

import com.yanovych.entities.Toy;
import com.yanovych.helpers.printers.EntityPrinter;
import com.yanovych.helpers.printers.ToyPrinter;
import com.yanovych.menu.MenuItem;
import com.yanovych.services.implementations.ToyServiceImplementation;
import com.yanovych.services.interfaces.ToyService;

import java.util.Scanner;

public class DeleteToyItem implements MenuItem {
    private final ToyService toyService = ToyServiceImplementation.getInstance();
    @Override
    public void doAction() {
        this.toyService.deleteToy(getToyFromListByUser());
    }

    private Toy getToyFromListByUser() {
        Toy toy = null;
        while (toy == null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("All toys: ");
            EntityPrinter printer = new ToyPrinter(this.toyService.getAllToys());
            printer.print();
            System.out.print("Enter toy id which you want delete -> ");
            Long id = scanner.nextLong();
            scanner.nextLine();
            toy  = this.toyService.getToyById(id);
            if (toy == null) {
                System.out.println("No toy with id " + id);
            }
        }
        return toy;
    }
}
