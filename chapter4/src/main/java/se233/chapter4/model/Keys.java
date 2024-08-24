package se233.chapter4.model;

import javafx.scene.input.KeyCode;

import java.util.HashMap;

public class Keys {
    private HashMap<KeyCode, Boolean> keys;

    public Keys() {
        keys = new HashMap<>();
    }

    public void add(KeyCode key) {
        keys.put(key, Boolean.TRUE);
    }

    public void remove(KeyCode key) {
        keys.put(key, Boolean.FALSE);
    }

    public boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }
}
