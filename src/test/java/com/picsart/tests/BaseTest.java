package com.picsart.tests;


import com.picsart.utils.Config;

public class BaseTest {

    private Config config;

    protected Config getConfig() {
        if (null == config) {
            config = new Config("app.properties");
        }
        return config;
    }
}
