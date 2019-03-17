package com.automation.dev.utils;

import java.io.*;

public class PropertiesUtil {

    private static final String PROPERTIES_FILE = "src/main/resources/config.properties";

    private static PropertiesUtil instance;
    private java.util.Properties properties;

    private PropertiesUtil(){}

    // Create a Singleton instance
    public static PropertiesUtil getInstance(){
        if (instance == null) {
            synchronized (PropertiesUtil.class){
                instance = new PropertiesUtil();
                instance.loadProperties();
            }
        }
        return instance;
    }


    public void loadProperties(){
        properties = new java.util.Properties();
        try {
            properties.load(new FileInputStream(PROPERTIES_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getValue(String propertyKey) {
        if (propertyKey != null) {
            return properties.getProperty(propertyKey);
        }
        return null;
    }
}
