package com.yanovych.menu.items.create;

import com.yanovych.entities.Toy;
import com.yanovych.entities.enums.Color;
import com.yanovych.entities.enums.ToyMaterial;
import com.yanovych.entities.enums.ToySize;
import com.yanovych.entities.enums.ToyType;
import com.yanovych.menu.MenuItem;
import com.yanovych.services.implementations.ToyServiceImplementation;
import com.yanovych.services.interfaces.ToyService;

import java.util.Arrays;
import java.util.Scanner;

public class CreateToyItem implements MenuItem {

    private final ToyService toyService = ToyServiceImplementation.getInstance();
    @Override
    public void doAction() {
        toyService.createToy(getToyFromKeyboardInput());
    }

    private Toy getToyFromKeyboardInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter toy name -> ");
        String name = scanner.nextLine();
        System.out.print("Enter minimum age for toy -> ");
        Integer minAge = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter price for toy -> ");
        Double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Choose type of toy: \n" + Arrays.toString(ToyType.values()) + "\n -> ");
        String type = scanner.nextLine().toUpperCase();
        System.out.print("Choose size of toy: \n" + Arrays.toString(ToySize.values()) + "\n -> ");
        String size = scanner.nextLine().toUpperCase();
        System.out.print("Choose color of toy: \n" + Arrays.toString(Color.values()) + "\n -> ");
        String color = scanner.nextLine().toUpperCase();
        System.out.print("Choose material of toy: \n" + Arrays.toString(ToyMaterial.values()) + "\n -> ");
        String material = scanner.nextLine().toUpperCase();
        return new Toy(name, minAge, price,
                ToyType.valueOf(type),
                ToySize.valueOf(size),
                Color.valueOf(color),
                ToyMaterial.valueOf(material));
    }
}
