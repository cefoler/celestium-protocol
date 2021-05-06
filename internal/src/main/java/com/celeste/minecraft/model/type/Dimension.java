package com.celeste.minecraft.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

/**
 * Represents all Minecraft default dimensions, all have a ID.
 */
@Getter
@AllArgsConstructor
public enum Dimension {

    NETHER(-1),
    OVERWORLD(0),
    END(1);

    private final int id;

    @Nullable
    public static Dimension getById(int id) {
        for (Dimension dimension : values()) {
            if (dimension.getId() == id) return dimension;
        }

        return null;
    }

}
