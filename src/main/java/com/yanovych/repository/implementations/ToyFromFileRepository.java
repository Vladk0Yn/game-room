package com.yanovych.repository.implementations;

import com.yanovych.repository.interfaces.ToyRepository;

public class ToyFromFileRepository implements ToyRepository {

    private static ToyFromFileRepository instance = null;

    private ToyFromFileRepository() {
    }

    public static ToyFromFileRepository getInstance() {
        if (instance == null) {
            instance = new ToyFromFileRepository();
        }
        return instance;
    }
}
