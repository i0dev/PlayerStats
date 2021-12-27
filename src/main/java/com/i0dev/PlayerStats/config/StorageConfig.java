package com.i0dev.PlayerStats.config;

import com.i0dev.PlayerStats.Heart;
import com.i0dev.PlayerStats.objects.StatStorage;
import com.i0dev.PlayerStats.templates.AbstractConfiguration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class StorageConfig extends AbstractConfiguration {

    public StorageConfig(Heart heart, String path) {
        this.path = path;
        this.heart = heart;
    }

    List<StatStorage> stats = new ArrayList<>();

}
