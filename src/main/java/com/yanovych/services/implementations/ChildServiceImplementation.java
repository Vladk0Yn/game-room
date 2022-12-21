package com.yanovych.services.implementations;

import com.yanovych.entities.Child;
import com.yanovych.repository.implementations.ChildFromFileRepository;
import com.yanovych.repository.interfaces.ChildRepository;
import com.yanovych.services.interfaces.ChildService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ChildServiceImplementation implements ChildService {

    private final ChildRepository childFileRepository = new ChildFromFileRepository();
    @Override
    public void createChild(Child child) {
        childFileRepository.addChild(child);
        log.info("IN create - child: {} successfully created", child.getName());
    }

    @Override
    public List<Child> getAllChildren() {
        return null;
    }
}
