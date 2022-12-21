package com.yanovych.repository.implementations;

import com.yanovych.repository.interfaces.RoomRepository;

public class RoomFromFileRepository implements RoomRepository {

    private static RoomFromFileRepository instance = null;

    private RoomFromFileRepository() {
    }

    public RoomFromFileRepository getInstance() {
        if (instance == null) {
            instance = new RoomFromFileRepository();
        }
        return instance;
    }


}
