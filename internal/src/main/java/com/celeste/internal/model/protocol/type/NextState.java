package com.celeste.internal.model.protocol.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

/**
 * Field received only on Handshake packet, should return STATUS or LOGIN.
 */
@Getter
@AllArgsConstructor
public enum NextState {

    STATUS(1),
    LOGIN(2);

    private final int id;

    /**
     * Get the NextState by the ID
     * @param id Integer
     *
     * @return NextState
     */
    @Nullable
    public static NextState getById(int id) {
        for (NextState state : values()) {
            if (state.getId() == id) return state;
        }

        return null;
    }

}
