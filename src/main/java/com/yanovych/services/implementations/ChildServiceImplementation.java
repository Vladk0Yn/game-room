package com.yanovych.services.implementations;

import com.yanovych.entities.Child;
import com.yanovych.repositories.implementations.ChildFromDbRepository;
import com.yanovych.repositories.implementations.ChildFromFileRepository;
import com.yanovych.repositories.interfaces.ChildRepository;
import com.yanovych.services.interfaces.ChildService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ChildServiceImplementation implements ChildService {
    private static ChildServiceImplementation instance = null;
    private ChildRepository childRepository = null;

    private ChildServiceImplementation() {
        String dataSource = System.getenv("DATA_SOURCE");
        dataSource = dataSource == null ? "file" : dataSource;
        switch (dataSource) {
            case "file" -> childRepository = ChildFromFileRepository.getInstance();
            case "db" -> childRepository = ChildFromDbRepository.getInstance();
            default -> {
                log.error("Error at reading environment variable DATA_SOURCE, default data source is file");
                childRepository = ChildFromFileRepository.getInstance();
            }
        }
    }

    public static ChildServiceImplementation getInstance() {
        if (instance == null) {
            instance = new ChildServiceImplementation();
        }
        return instance;
    }

    @Override
    public Child getChildById(Long id) {
        Child child = this.childRepository.getChildById(id);
        if (child == null) {
            log.error("IN getChildById - no child with id: {}", id);
            return null;
        }
        log.info("IN getChildById - child: {} successfully found", child.getName());
        return child;
    }

    @Override
    public void createChild(Child child) {
        childRepository.addChild(child);
        log.info("IN createChild - child: {} successfully created", child.getName());
    }

    @Override
    public List<Child> getAllChildren() {
        List<Child> children = childRepository.getAllChildren();
        log.info("IN getAllChildren - children: {} successfully received", children.size());
        return children;
    }
}
