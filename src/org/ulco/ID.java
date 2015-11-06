package org.ulco;

public class ID {
    private static ID instance;
    private int id = 0;

    private ID() {
    }

    public static ID getInstance() {
        synchronized (ID.class) {
            if (instance == null) {
                instance = new ID();
            }
            return instance;
        }
    }

    public int currentId() {
        return id;
    }

    public int nextId() {
        return id++;
    }
}