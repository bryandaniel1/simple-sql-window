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
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

/**
 * This SwingWorker runs the connection test in a background thread and
 * updates the result label when complete.
 *
 * @author Bryan Daniel
 */
public class ConnectionTestWorker extends SwingWorker<String, Object> {

    /**
     * The object holding connection properties
     */
    private final ConnectionProperties properties;

    /**
     * The label to update with a message
     */
    private final JLabel resultLabel;

    /**
     * This constructor sets all the values of the instance variables.
     *
     * @param properties the connection properties
     * @param resultLabel the result label
     */
    public ConnectionTestWorker(ConnectionProperties properties, JLabel resultLabel) {
        this.properties = properties;
        this.resultLabel = resultLabel;
    }

    /**
     * This method gets the result message produced by the doInBackground method
     * and displays the message on the result label. This is executed on the
     * event dispatch thread.
     */
    @Override
    protected void done() {

        try {
            resultLabel.setText(get());
            resultLabel.repaint();
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(ConnectionTestWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method uses the data access class for executing a test query to
     * verify the connection parameters are good. This is not executed on the
     * event dispatch thread.
     *
     * @return the result message
     * @throws Exception if an exception occurs
     */
    @Override
    protected String doInBackground() throws Exception {

        String resultMessage;
        TestQueryDataAccess testQuery = new TestQueryDataAccess(properties);
        if (testQuery.connectionSuccessful()) {
            resultMessage = "Connection successful!";
        } else {
            resultMessage = "Connection failed!";
        }
        return resultMessage;
    }

}
