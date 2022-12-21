package com.yanovych.entities;

import com.yanovych.entities.enums.Sex;
import lombok.*;

@AllArgsConstructor
@Data
public class Child {
    private Long id;
    private String name;
    private Integer age;
    private Sex sex;
    private Room room;
}
