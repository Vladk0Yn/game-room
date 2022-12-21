package com.yanovych;

import com.yanovych.menu.MainMenu;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

@Slf4j
public class Main {

    public static void main(String[] args) {

        log.info("Program started");

        Scanner scanner = new Scanner(System.in);
        MainMenu menu = new MainMenu();

        while (true) {
            System.out.println("Available commands: " + menu.getAvailableCommands());
            System.out.print("Choose action -> ");
            menu.execute(scanner.nextLine());
        }
    }
}