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
package com.daniel.simplesql.logic;

import com.daniel.simplesql.data.TestQueryDataAccess;
import com.daniel.simplesql.model.ConnectionProperties;
import java.util.concurrent.Callable;

/**
 * This callable class executes a method to validate database connection
 * properties.
 *
 * @author Bryan Daniel
 */
public class TestQueryRunner implements Callable<Boolean> {

    /**
     * The object holding connection properties
     */
    ConnectionProperties properties;

    /**
     * This constructor sets the connection properties for the test query.
     *
     * @param properties the connection properties
     */
    public TestQueryRunner(ConnectionProperties properties) {
        this.properties = properties;
    }

    /**
     * This method uses the data access class for executing a test query to
     * validate the connection information, returning a pass or fail result.
     *
     * @return the indication of a passing or failing test
     * @throws Exception if an exception occurs
     */
    @Override
    public Boolean call() throws Exception {

        TestQueryDataAccess testQuery = new TestQueryDataAccess(properties);
        if (testQuery.connectionSuccessful()) {
            return true;
        }
        return false;
    }
}
