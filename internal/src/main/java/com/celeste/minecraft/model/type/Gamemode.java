package com.celeste.minecraft.model.type;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

/**
 * Represents all Minecraft gamemodes, all have a ID.
 */
@Getter
@AllArgsConstructor
public enum Gamemode {

    SURVIVAL(0),
    CREATIVE(1),
    ADVENTURE(2),
    SPECTATOR(3);

    private final int id;

    @Nullable
    public static Gamemode getById(int id) {
        return Arrays.stream(values()).filter(gamemode -> gamemode.getId() == id)
            .findFirst()
            .orElse(null);
    }

}
