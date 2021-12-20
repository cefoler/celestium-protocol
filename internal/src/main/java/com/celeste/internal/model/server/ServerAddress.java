package com.celeste.internal.model.server;

/**
 * The ServerAddress represents the model used
 * to build the credentials to connect into the Server
 */
public record ServerAddress(String address, int port) {

}
