package com.yanovych.menu.items.print;

import com.yanovych.entities.Toy;
import com.yanovych.menu.MenuItem;
import com.yanovych.services.implementations.ToyServiceImplementation;
import com.yanovych.services.interfaces.ToyService;

import java.util.List;

public class PrintToysItem implements MenuItem {

    private final ToyService toyService = ToyServiceImplementation.getInstance();
    @Override
    public void doAction() {
        List<Toy> toys = toyService.getAllToys();
        for(Toy toy : toys) {
            System.out.println("-----------------------------------");
            System.out.println("ID: " + toy.getId());
            System.out.println("Name: " + toy.getName());
            System.out.println("Minimum age: " + toy.getMinimumAge());
            System.out.println("Price: " + toy.getPrice());
            System.out.println("Type: " + toy.getType().toString());
            System.out.println("Size: " + toy.getSize().toString());
            System.out.println("Color: " + toy.getColor().toString());
            System.out.println("Material: " + toy.getMaterial().toString());
            System.out.println("");
        }
    }
}
