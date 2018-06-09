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
package com.daniel.simplesql.data;

import com.daniel.simplesql.logic.TestQueryRunner;
import com.daniel.simplesql.model.ConnectionProperties;
import com.daniel.simplesql.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class uses the database connection properties to attempt a test query
 * for connection validation.
 *
 * @author Bryan Daniel
 */
public class TestQueryDataAccess {

    /**
     * The database connection properties
     */
    ConnectionProperties properties;

    /**
     * This constructor sets the connection properties for the test query.
     *
     * @param properties the connection properties
     */
    public TestQueryDataAccess(ConnectionProperties properties) {
        this.properties = properties;
    }

    /**
     * This method executes the test query and returns a boolean value
     * indicating a pass or fail result.
     *
     * @return the indication of a passing or failing test
     */
    public Boolean connectionSuccessful() {

        Connection connection = null;
        Statement query = null;
        ResultSet resultSet = null;
        Boolean testPasses = false;

        StringBuilder connectionString = new StringBuilder();
        connectionString.append(properties.getProperty(ConnectionProperties.DRIVER_STRING));
        connectionString.append(properties.getProperty(ConnectionProperties.HOST));
        connectionString.append(":");
        connectionString.append(properties.getProperty(ConnectionProperties.PORT));
        connectionString.append("/");
        connectionString.append(properties.getProperty(ConnectionProperties.DATABASE_NAME));
        try {
            connection = DriverManager.getConnection(connectionString.toString(),
                    properties.getProperty(ConnectionProperties.USERNAME),
                    properties.getProperty(ConnectionProperties.PASSWORD));
            query = connection.createStatement();
            resultSet = query.executeQuery("SELECT 1 FROM DUAL");
            resultSet.next();
            int result = resultSet.getInt(1);
            if (result == 1) {
                testPasses = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestQueryDataAccess.class.getName()).log(Level.INFO,
                    "Connection exception occurred during TestQueryDataAccess.connectionSuccessful.", ex);
        } finally {
            DatabaseUtil.closeResultSet(resultSet);
            DatabaseUtil.closeStatement(query);
            DatabaseUtil.closeConnection(connection);
        }
        return testPasses;
    }
}
