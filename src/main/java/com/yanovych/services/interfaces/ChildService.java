package com.yanovych.services.interfaces;

import com.yanovych.entities.Child;
import com.yanovych.entities.Room;

import java.util.List;

public interface ChildService {
    Child getChildById(Long id);
    void createChild(Child child);
    List<Child> getAllChildren();
}
