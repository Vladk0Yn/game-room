package com.yanovych.menu.items.create;

import com.yanovych.entities.Child;
import com.yanovych.entities.enums.Sex;
import com.yanovych.menu.MenuItem;
import com.yanovych.services.implementations.ChildServiceImplementation;
import com.yanovych.services.interfaces.ChildService;

import java.util.Arrays;
import java.util.Scanner;

public class CreateChildItem implements MenuItem {
    private final ChildService childService = ChildServiceImplementation.getInstance();
    @Override
    public void doAction() {
        childService.createChild(getChildFromKeyboardInput());
    }

    private Child getChildFromKeyboardInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter child name -> ");
        String name = scanner.nextLine();
        System.out.print("Enter child age -> ");
        Integer age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Choose sex of child: \n" + Arrays.toString(Sex.values()) + "\n -> ");
        String sex = scanner.nextLine();

        return new Child(name, age, Sex.valueOf(sex.toUpperCase()));
    }
}
