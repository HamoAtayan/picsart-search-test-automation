package com.picsart.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

@Slf4j
public class Config {

    private PropertiesConfiguration properties;

    public Config(String path) {
        try {
            properties = new PropertiesConfiguration("src/test/resources/" + path);
        } catch (ConfigurationException ex) {
            ex.getMessage();
        }
    }

    public String get(String name) {
        return (String) properties.getProperty(name);
    }
}
