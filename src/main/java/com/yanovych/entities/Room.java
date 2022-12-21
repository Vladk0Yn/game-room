package com.yanovych.entities;

import lombok.*;

import java.util.List;

@AllArgsConstructor
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
}
