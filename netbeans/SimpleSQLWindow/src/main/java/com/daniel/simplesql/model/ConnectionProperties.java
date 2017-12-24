/*
 * Copyright 2017 Bryan Daniel.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.daniel.simplesql.model;

import java.util.Properties;
import org.apache.commons.lang3.Validate;

/**
 * This properties class stores values for database connections.
 *
 * @author Bryan Daniel
 */
public class ConnectionProperties extends Properties {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 8070837690193560185L;

    /**
     * The key for the driver string
     */
    public static final String DRIVER_STRING = "driverString";

    /**
     * The key for the host
     */
    public static final String HOST = "host";

    /**
     * The key for the database name
     */
    public static final String DATABASE_NAME = "databaseName";

    /**
     * The key for the port
     */
    public static final String PORT = "port";

    /**
     * The key for the username
     */
    public static final String USERNAME = "username";

    /**
     * The key for the password
     */
    public static final String PASSWORD = "password";

    /**
     * This constructor sets the property values with the given parameters.
     *
     * @param driver the driver string
     * @param host the host
     * @param databaseName the database name
     * @param port the port
     * @param username the username
     * @param password the password
     */
    public ConnectionProperties(String driver, String host, String databaseName,
            String port, String username, String password) {
        validateAndSet(driver, host, databaseName, port, username, password);
    }

    /**
     * This method validates property values before setting them.
     *
     * @param driver the driver string
     * @param host the host
     * @param databaseName the database name
     * @param port the port
     * @param username the username
     * @param password the password
     */
    private void validateAndSet(String driver, String host, String databaseName,
            String port, String username, String password) {
        Validate.notEmpty(driver, "driver must be provided");
        Validate.notEmpty(host, "host must be provided");
        Validate.notEmpty(databaseName, "databaseName must be provided");
        Validate.notEmpty(port, "port must be provided");
        Validate.notEmpty(username, "username must be provided");
        Validate.notEmpty(password, "password must be provided");
        setProperty(ConnectionProperties.DRIVER_STRING, driver);
        setProperty(ConnectionProperties.HOST, "127.0.0.1");
        setProperty(ConnectionProperties.DATABASE_NAME, databaseName);
        setProperty(ConnectionProperties.PORT, port);
        setProperty(ConnectionProperties.USERNAME, username);
        setProperty(ConnectionProperties.PASSWORD, password);
    }
}
