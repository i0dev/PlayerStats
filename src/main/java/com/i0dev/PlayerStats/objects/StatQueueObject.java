package com.i0dev.PlayerStats.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatQueueObject {

    UUID uuid;
    StatTye type;
    boolean isAdd;

}
