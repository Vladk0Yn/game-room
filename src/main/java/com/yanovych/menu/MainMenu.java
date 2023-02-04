package com.yanovych.menu;

import com.yanovych.menu.items.ExitItem;
import com.yanovych.menu.items.FindToysInRoomItem;
import com.yanovych.menu.items.SortToysInRoomItem;
import com.yanovych.menu.items.add.AddChildToRoomItem;
import com.yanovych.menu.items.add.AddToyToRoom;
import com.yanovych.menu.items.create.CreateChildItem;
import com.yanovych.menu.items.create.CreateRoomItem;
import com.yanovych.menu.items.create.CreateToyItem;
import com.yanovych.menu.items.delete.DeleteChildItem;
import com.yanovych.menu.items.delete.DeleteRoomItem;
import com.yanovych.menu.items.delete.DeleteToyItem;
import com.yanovych.menu.items.edit.EditChildItem;
import com.yanovych.menu.items.edit.EditRoomItem;
import com.yanovych.menu.items.edit.EditToyItem;
import com.yanovych.menu.items.print.PrintChildrenItem;
import com.yanovych.menu.items.print.PrintRoomsItem;
import com.yanovych.menu.items.print.PrintToysItem;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class MainMenu {
    private final Map<String, MenuItem> menuItems;

    public MainMenu() {
        menuItems = new LinkedHashMap<>();
        menuItems.put("new-child", new CreateChildItem());
        menuItems.put("new-room", new CreateRoomItem());
        menuItems.put("new-toy", new CreateToyItem());
        menuItems.put("add-child", new AddChildToRoomItem());
        menuItems.put("add-toy", new AddToyToRoom());
        menuItems.put("print-children", new PrintChildrenItem());
        menuItems.put("print-toys", new PrintToysItem());
        menuItems.put("print-rooms", new PrintRoomsItem());
        menuItems.put("edit-child", new EditChildItem());
        menuItems.put("edit-toy", new EditToyItem());
        menuItems.put("edit-room", new EditRoomItem());
        menuItems.put("delete-child", new DeleteChildItem());
        menuItems.put("delete-toy", new DeleteToyItem());
        menuItems.put("delete-room", new DeleteRoomItem());
        menuItems.put("sort", new SortToysInRoomItem());
        menuItems.put("search", new FindToysInRoomItem());
        menuItems.put("exit", new ExitItem());
    }

    public void execute(String command) {
        menuItems.getOrDefault(command.toLowerCase(), () -> System.out.println("Incorrect command, try again!")).doAction();
    }

    public Set<String> getAvailableCommands() {
        return menuItems.keySet();
    }
}
