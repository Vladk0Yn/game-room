package com.yanovych.menu.items.create;

import com.yanovych.menu.MenuItem;
import com.yanovych.services.implementations.ChildServiceImplementation;
import com.yanovych.services.interfaces.ChildService;

public class CreateChildItem implements MenuItem {
    private final ChildService childService = new ChildServiceImplementation();
    @Override
    public void doAction() {

    }
}
