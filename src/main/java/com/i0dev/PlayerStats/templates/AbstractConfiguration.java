package com.i0dev.PlayerStats.templates;

import com.i0dev.PlayerStats.Heart;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractConfiguration {

    public transient Heart heart = null;
    public transient String path = "";

}
