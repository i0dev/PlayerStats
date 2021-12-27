package com.i0dev.PlayerStats.objects;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Location {
    double x, y, z;
    String worldName;
}