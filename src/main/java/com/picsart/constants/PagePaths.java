package com.picsart.constants;

public enum PagePaths {
    IMAGES("images/");
    private final String path;

    PagePaths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
