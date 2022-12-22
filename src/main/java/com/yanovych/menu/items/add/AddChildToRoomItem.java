package com.yanovych.menu.items.add;

import com.yanovych.menu.MenuItem;
import com.yanovych.services.implementations.ChildServiceImplementation;
import com.yanovych.services.implementations.RoomServiceImplementation;
import com.yanovych.services.interfaces.ChildService;
import com.yanovych.services.interfaces.RoomService;

public class AddChildToRoomItem implements MenuItem {
    private final ChildService childService = ChildServiceImplementation.getInstance();
    private final RoomService roomService = RoomServiceImplementation.getInstance();
    @Override
    public void doAction() {
        this.roomService.addChildToRoom(this.childService.getAllChildren().get(1), this.roomService.getAllRooms().get(0));
    }
}
