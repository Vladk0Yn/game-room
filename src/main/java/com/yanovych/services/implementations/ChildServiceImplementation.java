package com.yanovych.services.implementations;

import com.yanovych.entities.Child;
import com.yanovych.repository.implementations.ChildFromFileRepository;
import com.yanovych.repository.interfaces.ChildRepository;
import com.yanovych.services.interfaces.ChildService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ChildServiceImplementation implements ChildService {
    private static ChildServiceImplementation instance = null;
    private final ChildRepository childFileRepository = ChildFromFileRepository.getInstance();

    private ChildServiceImplementation() {
    }

    public static ChildServiceImplementation getInstance() {
        if (instance == null) {
            instance = new ChildServiceImplementation();
        }
        return instance;
    }

    @Override
    public void createChild(Child child) {
        childFileRepository.addChild(child);
        log.info("IN create - child: {} successfully created", child.getName());
    }

    @Override
    public List<Child> getAllChildren() {
        List<Child> children = childFileRepository.getAllChildren();
        log.info("IN getAll - children: {} successfully received", children.size());
        return children;
    }
}
