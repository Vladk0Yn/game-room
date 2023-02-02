package com.yanovych.menu.items.delete;

import com.yanovych.entities.Child;
import com.yanovych.helpers.printers.ChildPrinter;
import com.yanovych.helpers.printers.EntityPrinter;
import com.yanovych.menu.MenuItem;
import com.yanovych.services.implementations.ChildServiceImplementation;
import com.yanovych.services.interfaces.ChildService;

import java.util.Scanner;

public class DeleteChildItem implements MenuItem {
    private final ChildService childService = ChildServiceImplementation.getInstance();
    @Override
    public void doAction() {
        this.childService.deleteChild(getChildFromListByUser());
    }

    private Child getChildFromListByUser() {
        Child child = null;
        while (child == null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("All children: ");
            EntityPrinter printer = new ChildPrinter(this.childService.getAllChildren());
            printer.print();
            System.out.print("Enter child id which you want delete -> ");
            Long id = scanner.nextLong();
            scanner.nextLine();
            child = this.childService.getChildById(id);
            if (child == null) {
                System.out.println("No child with id " + id);
            }
        }
        return child;
    }
}
