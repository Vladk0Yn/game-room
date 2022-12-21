package com.yanovych.entities;

import com.yanovych.entities.enums.Color;
import com.yanovych.entities.enums.ToyMaterial;
import com.yanovych.entities.enums.ToySize;
import com.yanovych.entities.enums.ToyType;
import lombok.*;

@AllArgsConstructor
@Data
public class Toy {
    private Long id;
    private String name;
    private Integer minimumAge;
    private Double price;
    private Room toyRoom;
    private ToyType type;
    private ToySize size;
    private Color color;
    private ToyMaterial material;
    private Room room;
}
