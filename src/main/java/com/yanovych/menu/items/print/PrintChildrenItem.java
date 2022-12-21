package com.yanovych.menu.items.print;

import com.yanovych.entities.Child;
import com.yanovych.menu.MenuItem;
import com.yanovych.services.implementations.ChildServiceImplementation;
import com.yanovych.services.interfaces.ChildService;

import java.util.List;

public class PrintChildrenItem implements MenuItem {

    private final ChildService childService = ChildServiceImplementation.getInstance();
    @Override
    public void doAction() {
        List<Child> children = childService.getAllChildren();
        for (Child child : children) {
            System.out.println("-----------------------------------");
            System.out.println("ID: " + child.getId());
            System.out.println("Name: " + child.getName());
            System.out.println("Age: " + child.getAge());
            System.out.println("Sex: " + child.getSex().toString().toLowerCase());
            if (child.getRoom() != null) {
                System.out.println("Room: " + child.getRoom());
            }
        }
        System.out.println("-----------------------------------");
    }
}
