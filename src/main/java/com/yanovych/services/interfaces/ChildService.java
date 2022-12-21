package com.yanovych.services.interfaces;

import com.yanovych.entities.Child;

import java.util.List;

public interface ChildService {
    void createChild(Child child);
    List<Child> getAllChildren();
}
