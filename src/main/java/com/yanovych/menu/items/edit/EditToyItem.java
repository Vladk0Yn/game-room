package com.yanovych.menu.items.edit;

import com.yanovych.entities.Toy;
import com.yanovych.entities.enums.Color;
import com.yanovych.entities.enums.ToyMaterial;
import com.yanovych.entities.enums.ToySize;
import com.yanovych.entities.enums.ToyType;
import com.yanovych.helpers.printers.EntityPrinter;
import com.yanovych.helpers.printers.ToyPrinter;
import com.yanovych.menu.MenuItem;
import com.yanovych.services.implementations.ToyServiceImplementation;
import com.yanovych.services.interfaces.ToyService;

import java.util.Arrays;
import java.util.Scanner;

public class EditToyItem implements MenuItem {

    private final ToyService toyService = ToyServiceImplementation.getInstance();
    @Override
    public void doAction() {
        this.toyService.updateToy(editToyFromKeyboardInput(getToyFromListByUser()));
    }

    private Toy getToyFromListByUser() {
        Toy toy = null;
        while (toy == null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("All toys: ");
            EntityPrinter printer = new ToyPrinter(this.toyService.getAllToys());
            printer.print();
            System.out.print("Enter toy id which you want edit -> ");
            Long id = scanner.nextLong();
            scanner.nextLine();
            toy  = this.toyService.getToyById(id);
            if (toy == null) {
                System.out.println("No toy with id " + id);
            }
        }
        return toy;
    }

    private Toy editToyFromKeyboardInput(Toy toy) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Edit toy name (current: "  + toy.getName() + ") -> ");
        String name = scanner.nextLine();
        System.out.print("Edit minimum age for toy (current: "  + toy.getMinimumAge() + ") -> ");
        Integer minAge = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Edit price for toy (current: "  + toy.getPrice() + ") -> ");
        Double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Edit type of toy (current: "  + toy.getType().toString() + "): \n" + Arrays.toString(ToyType.values()) + "\n -> ");
        String type = scanner.nextLine().toUpperCase();
        System.out.print("Edit size of toy (current: "  + toy.getSize().toString() + "): \n" + Arrays.toString(ToySize.values()) + "\n -> ");
        String size = scanner.nextLine().toUpperCase();
        System.out.print("Edit color of toy (current: "  + toy.getColor().toString() + "): \n" + Arrays.toString(Color.values()) + "\n -> ");
        String color = scanner.nextLine().toUpperCase();
        System.out.print("Edit material of toy (current: "  + toy.getMaterial().toString() + "): \n" + Arrays.toString(ToyMaterial.values()) + "\n -> ");
        String material = scanner.nextLine().toUpperCase();

        toy.setName(name);
        toy.setMinimumAge(minAge);
        toy.setPrice(price);
        toy.setType(ToyType.valueOf(type));
        toy.setSize(ToySize.valueOf(size));
        toy.setColor(Color.valueOf(color));
        toy.setMaterial(ToyMaterial.valueOf(material));

        return toy;
    }
}
