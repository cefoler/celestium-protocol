package com.celeste.internal.model.server;

import lombok.AllArgsConstructor;

/**
 * The ServerAddress represents the model used
 * to build the credentials to connect into the Server
 */
@AllArgsConstructor
public record ServerAddress(String address, int port) {

}
