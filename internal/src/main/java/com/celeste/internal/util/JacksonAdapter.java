package com.celeste.internal.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

public final class JacksonAdapter {

    private static final JacksonAdapter INSTANCE = new JacksonAdapter();

    private final ObjectMapper mapper;

    private JacksonAdapter() {
        this.mapper = new ObjectMapper();
    }

    /**
     * Turns a json string into object
     * @param json String
     * @param clazz Object
     * @param <T> T
     *
     * @return T
     * @throws IOException Throws when there is a error reading the json into that class
     */
    @NotNull
    public <T> T fromJson(@NotNull final String json, @NotNull final Class<T> clazz) throws IOException {
        return mapper.readValue(json, clazz);
    }

    /**
     * Turns that object into a json string
     * @param value Object
     *
     * @return String
     * @throws IOException Throws when there is a error reading the object
     */
    @NotNull
    public String toJson(@NotNull final Object value) throws IOException {
        return mapper.writeValueAsString(value);
    }

    /**
     * Creates a ObjectNode
     * @return ObjectNode
     */
    @NotNull
    public ObjectNode createNode() {
        return mapper.createObjectNode();
    }

    /**
     * Gets a ObjectNode from a json string
     * @param json String
     *
     * @return ObjectNode
     * @throws IOException Throws when there is a error reading the json string
     */
    @NotNull
    public ObjectNode getNode(@NotNull final String json) throws IOException {
        return mapper.readValue(json, ObjectNode.class);
    }

    /**
     * Gets a JsonNode from a ObjectNode
     * @param node ObjectNode
     * @param key String
     *
     * @return JsonNode
     */
    @NotNull
    public JsonNode get(@NotNull final ObjectNode node, @NotNull final String key) {
        return Objects.requireNonNull(node.get(key), "The " + key + " parameter was not found in the node");
    }

    /**
     * Gets String from the ObjectNode
     * @param node ObjectNode
     * @param key String
     *
     * @return String
     */
    @NotNull
    public String getString(@NotNull final ObjectNode node, @NotNull final String key) {
        return get(node, key).textValue();
    }

    /**
     * Gets int from the ObjectNode
     * @param node ObjectNode
     * @param key String
     *
     * @return int
     */
    public int getInt(@NotNull final ObjectNode node, @NotNull final String key) {
        return get(node, key).intValue();
    }

    /**
     * Gets boolean from the ObjectNode
     * @param node ObjectNode
     * @param key String
     *
     * @return boolean
     */
    public boolean getBoolean(@NotNull final ObjectNode node, @NotNull final String key) {
        return get(node, key).booleanValue();
    }

    /**
     * Gets double from the ObjectNode
     * @param node ObjectNode
     * @param key String
     *
     * @return double
     */
    public double getDouble(@NotNull final ObjectNode node, @NotNull final String key) {
        return get(node, key).doubleValue();
    }

    /**
     * Gets float from the ObjectNode
     * @param node ObjectNode
     * @param key String
     *
     * @return float
     */
    public float getFloat(@NotNull final ObjectNode node, @NotNull final String key) {
        return get(node, key).floatValue();
    }

    /**
     * @return {@code JacksonAdapter}
     */
    public static JacksonAdapter getInstance() {
        return INSTANCE;
    }

}