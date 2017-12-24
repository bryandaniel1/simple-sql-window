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

import com.daniel.simplesql.model.ConnectionProperties;
import com.daniel.simplesql.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 * This data-access class provides the functionality for taking any statement
 * and executing that statement using provided connection properties.
 *
 * @author Bryan Daniel
 */
public class AnyQueryDataAccess {

    /**
     * This method uses the input string and the connection properties to access
     * a database and retrieve results for the query or queries in the string.
     *
     * @param queryString the query string
     * @param properties the database connection properties
     * @return the list of results
     */
    public ArrayList<Object> executeStatement(String queryString, ConnectionProperties properties) {

        Connection connection = null;
        Statement query = null;
        ResultSet resultSet = null;
        ArrayList<Object> allResults = new ArrayList<>();

        StringBuilder connectionString = new StringBuilder();
        connectionString.append(properties.getProperty(ConnectionProperties.DRIVER_STRING));
        connectionString.append(properties.getProperty(ConnectionProperties.HOST));
        connectionString.append(":");
        connectionString.append(properties.getProperty(ConnectionProperties.PORT));
        connectionString.append("/");
        connectionString.append(properties.getProperty(ConnectionProperties.DATABASE_NAME));
        connectionString.append("?allowMultiQueries=true");
        try {
            connection = DriverManager.getConnection(connectionString.toString(),
                    properties.getProperty(ConnectionProperties.USERNAME),
                    properties.getProperty(ConnectionProperties.PASSWORD));
            query = connection.createStatement();

            // true indicates the first result is a result set
            boolean moreResults = query.execute(queryString);
            int updateCount = query.getUpdateCount();
            while (updateCount != -1 || moreResults) {

                if (moreResults) {
                    resultSet = query.getResultSet();
                    ResultSetMetaData metaData = resultSet.getMetaData();

                    // names of columns
                    Vector<String> columnNames = new Vector<>();
                    int columnCount = metaData.getColumnCount();
                    for (int column = 1; column <= columnCount; column++) {
                        columnNames.add(metaData.getColumnName(column));
                    }
                    
                    // data of the table
                    Vector<Vector<Object>> data = new Vector<>();
                    while (resultSet.next()) {
                        Vector<Object> vector = new Vector<Object>();
                        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                            vector.add(resultSet.getObject(columnIndex));
                        }
                        data.add(vector);
                    }
                    allResults.add(new DefaultTableModel(data, columnNames));
                }
                if (updateCount != -1) {
                    allResults.add("Total records updated: " + updateCount);
                }
                moreResults = query.getMoreResults();
                updateCount = query.getUpdateCount();
            }
        } catch (SQLException e) {
            Logger.getLogger(AnyQueryDataAccess.class.getName()).log(Level.INFO,
                    "Connection exception occurred during TestQueryDataAccess.connectionSuccessful.", e);
            allResults.add(e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(resultSet);
            DatabaseUtil.closeStatement(query);
            DatabaseUtil.closeConnection(connection);
        }
        return allResults;
    }
}
