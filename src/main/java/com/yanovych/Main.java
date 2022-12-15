package com.yanovych;

import com.yanovych.menu.MainMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MainMenu menu = new MainMenu();
        while (true) {
            System.out.println("Available commands: " + menu.getAvailableCommands());
            System.out.print("Choose action -> ");
            menu.execute(scanner.nextLine());
        }
    }
}