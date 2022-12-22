package com.yanovych.repository.implementations;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yanovych.entities.Child;
import com.yanovych.entities.Room;
import com.yanovych.helpers.ObjectFileReader;
import com.yanovych.helpers.ObjectFileWriter;
import com.yanovych.repository.interfaces.ChildRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ChildFromFileRepository implements ChildRepository {
    private List<Child> children = this.getAllChildren();
    private static ChildFromFileRepository instance = null;

    private ChildFromFileRepository() {
    }

    public static ChildFromFileRepository getInstance() {
        if (instance == null) {
            instance = new ChildFromFileRepository();
        }
        return instance;
    }

    @Override
    public List<Child> getAllChildren() {
        String childrenListJson = ObjectFileReader.read("children.json");
        return new Gson().fromJson(childrenListJson, new TypeToken<List<Child>>(){}.getType());
    }

    @Override
    public void addChild(Child child) {
        if (this.children == null || this.children.isEmpty()) {
            this.children = new ArrayList<>();
            child.setId(1L);
        } else {
            Long lastChildId = this.children.stream().max(Comparator.comparingLong(Child::getId)).get().getId();
            child.setId(++lastChildId);
        }
        this.children.add(child);
        String childrenJsonFormat = new Gson().toJson(this.children);
        ObjectFileWriter.write("children.json", childrenJsonFormat, false);
    }

    @Override
    public void updateChild(Child child) {
        for (int i = 0; i < this.children.size(); i++) {
            if (child.getId().equals(this.children.get(i).getId())) {
                this.children.set(i, child);
                String roomsJsonFormat = new Gson().toJson(this.children);
                ObjectFileWriter.write("children.json", roomsJsonFormat, false);
                break;
            }
        }
    }

    @Override
    public void addChildToRoom(Child child, Room room) {
        child.setRoomId(room.getId());
        this.updateChild(child);
    }
}
