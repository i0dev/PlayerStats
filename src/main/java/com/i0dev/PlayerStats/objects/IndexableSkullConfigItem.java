package com.i0dev.PlayerStats.objects;

import lombok.Data;

import java.util.List;

@Data
public class IndexableSkullConfigItem extends IndexableConfigItem {

    String skullData;

    public IndexableSkullConfigItem(String displayName, int amount, short data, String material, List<String> lore, boolean glow, int index, String skullData) {
        super(displayName, amount, data, material, lore, glow, index);
        this.skullData = skullData;
    }
}
