/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Asus
 */
public class Configuration {
    public static boolean readProperties() {
        Properties props = null;

        try {
            File configFile = new File(Constantes.PARAMS_FILE);
            FileReader reader = new FileReader(configFile);
            props = new Properties();
            props.load(reader);
            reader.close();

            databaseUrl = props.getProperty("database.url");
            databaseUsername = props.getProperty("database.username");
            databasePassword = props.getProperty("database.password");
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    public static String getDatabaseUrl() {
        { readProperties(); } return databaseUrl;
    }

    public static String getDatabaseUsername() {
        { readProperties(); } return databaseUsername;
    }

    public static String getDatabasePassword() {
        { readProperties(); } return databasePassword;
    }


    static String databaseUrl;
    static String databaseUsername;
    static String databasePassword;
}
