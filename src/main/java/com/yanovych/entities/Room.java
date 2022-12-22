package com.yanovych.entities;

import lombok.*;

import java.util.List;

@Data
public class Room {
    private Long id;
    private String name;
    private Integer capacity;
    private Double budget;
    private Integer minimumChildAge;
    private Integer maximumChildAge;
    private List<Child> childrenInRoom;
    private List<Toy> toysInRoom;

    public Room(String name, Integer capacity, Double budget, Integer minimumChildAge, Integer maximumChildAge) {
        this.name = name;
        this.capacity = capacity;
        this.budget = budget;
        this.minimumChildAge = minimumChildAge;
        this.maximumChildAge = maximumChildAge;
    }
}
