package com.celeste.internal.model.client;

import com.celeste.internal.model.client.type.ChatMode;

import java.util.Locale;

public record ClientSettings(Locale local, int viewDistance, ChatMode mode, boolean chatColors, int mainHand) {

}
