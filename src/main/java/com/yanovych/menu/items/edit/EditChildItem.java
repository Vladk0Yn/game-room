package com.yanovych.menu.items.edit;

import com.yanovych.entities.Child;
import com.yanovych.entities.enums.Sex;
import com.yanovych.helpers.printers.ChildPrinter;
import com.yanovych.helpers.printers.EntityPrinter;
import com.yanovych.menu.MenuItem;
import com.yanovych.services.implementations.ChildServiceImplementation;
import com.yanovych.services.interfaces.ChildService;

import java.util.Arrays;
import java.util.Scanner;

public class EditChildItem implements MenuItem {

    private final ChildService childService = ChildServiceImplementation.getInstance();
    @Override
    public void doAction() {
        this.childService.updateChild(editChildFromKeyboardInput(getChildFromListByUser()));
    }

    private Child getChildFromListByUser() {
        Child child = null;
        while (child == null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("All children: ");
            EntityPrinter printer = new ChildPrinter(this.childService.getAllChildren());
            printer.print();
            System.out.print("Enter child id which you want edit -> ");
            Long id = scanner.nextLong();
            scanner.nextLine();
            child = this.childService.getChildById(id);
            if (child == null) {
                System.out.println("No child with id " + id);
            }
        }
        return child;
    }

    private Child editChildFromKeyboardInput(Child child) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Edit child name (current: " + child.getName() + ") -> ");
        String name = scanner.nextLine();
        System.out.print("Edit child age (current: " + child.getAge() + ") -> ");
        Integer age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Edit sex of child (current: " + child.getSex() + "): \n" + Arrays.toString(Sex.values()) + "\n -> ");
        String sex = scanner.nextLine();

        child.setName(name);
        child.setAge(age);
        child.setSex(Sex.valueOf(sex.toUpperCase()));

        return child;
    }
}
