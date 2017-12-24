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
package com.daniel.simplesql.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class provides utility methods for database access functions.
 *
 * @author Bryan Daniel
 */
public class DatabaseUtil {
    
    /**
     * Private constructor - not called
     */
    public DatabaseUtil(){        
    }

    /**
     * This utility method closes the given result set.
     *
     * @param resultSet the result set
     */
    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseUtil.class.getName()).log(Level.INFO,
                        "Connection exception occurred closing the result set.", ex);
            }
        }
    }

    /**
     * This utility method closes the given statement.
     *
     * @param statement the statement
     */
    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseUtil.class.getName()).log(Level.INFO,
                        "Connection exception occurred closing the statement.", ex);
            }
        }
    }

    /**
     * This utility method closes the given connection.
     *
     * @param connection the connection
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseUtil.class.getName()).log(Level.INFO,
                        "Connection exception occurred closing the connection.", ex);
            }
        }
    }
}
