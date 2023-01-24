package com.yanovych.repositories.implementations;

import com.yanovych.entities.Child;
import com.yanovych.entities.Room;
import com.yanovych.repositories.interfaces.ChildRepository;

import java.util.List;

public class ChildFromDbRepository implements ChildRepository {
    private static ChildFromDbRepository instance = null;
    public static ChildFromDbRepository getInstance() {
        if (instance == null) {
            instance = new ChildFromDbRepository();
        }
        return instance;
    }
    @Override
    public Child getChildById(Long id) {
        return null;
    }

    @Override
    public List<Child> getAllChildren() {
        return null;
    }

    @Override
    public void addChild(Child child) {

    }

    @Override
    public void updateChild(Child child) {

    }

    @Override
    public void addChildToRoom(Child child, Room room) {

    }
}
