package com.yanovych.repository.interfaces;

import com.yanovych.entities.Child;

import java.util.List;

public interface ChildRepository {
    Child getChildById(long id);
    List<Child> getAllChildren();
    void addChild(Child child);
}
