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
    public Child getChildById(Long id) {
        Child child = this.childFileRepository.getChildById(id);
        if (child == null) {
            log.error("IN getChildById - no child with id: {}", id);
            return null;
        }
        log.info("IN getChildById - child: {} successfully found", child.getName());
        return child;
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

    @Override
    public List<Child> getChildrenWithoutRoom() {
        return getAllChildren().stream().filter(c -> c.getRoomId() == null).toList();
    }
}
