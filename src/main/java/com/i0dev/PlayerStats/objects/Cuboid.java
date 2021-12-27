package com.i0dev.PlayerStats.objects;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Cuboid {
    double xMin, yMin, zMin;
    double xMax, yMax, zMax;
    String world;
}