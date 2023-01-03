package com.yanovych.services.implementations;

import com.yanovych.repository.implementations.ToyFromFileRepository;
import com.yanovych.repository.interfaces.ToyRepository;
import com.yanovych.services.interfaces.ToyService;

public class ToyServiceImplementation implements ToyService {
    private static ToyServiceImplementation instance = null;
    private final ToyRepository toyRepository = ToyFromFileRepository.getInstance();
    private ToyServiceImplementation() {
    }

    public static ToyServiceImplementation getInstance() {
        if (instance == null) {
            instance = new ToyServiceImplementation();
        }
        return instance;
    }
}
