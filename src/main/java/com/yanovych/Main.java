package com.yanovych;

import com.yanovych.helpers.ConnectionManager;
import com.yanovych.menu.MainMenu;
import lombok.extern.slf4j.Slf4j;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

@Slf4j
public class Main {

    public static void main(String[] args) {

        log.info("Program started");
        Scanner scanner = new Scanner(System.in);
        MainMenu menu = new MainMenu();

        while (true) {
            System.out.println("Available commands:\n" + menu.getAvailableCommands());
            System.out.print("Choose action -> ");
            menu.execute(scanner.nextLine());
        }
    }
}