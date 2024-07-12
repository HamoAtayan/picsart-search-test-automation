package com.picsart.constants;

public enum Endpoints {
    USERS("/users"),
    POSTS("/posts");
    private final String path;

    Endpoints(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
