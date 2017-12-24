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
package com.daniel.simplesql.controller;

import com.daniel.simplesql.logic.TestQueryRunner;
import com.daniel.simplesql.logic.ConnectionTestWorker;
import com.daniel.simplesql.model.ConnectionProperties;
import static com.daniel.simplesql.ui.ConnectionSetupWindow.CONNECT_COMMAND;
import static com.daniel.simplesql.ui.ConnectionSetupWindow.EXIT_COMMAND;
import static com.daniel.simplesql.ui.ConnectionSetupWindow.TEST_COMMAND;
import com.daniel.simplesql.ui.QueryWindow;
import com.daniel.simplesql.ui.WindowAssembler;
import com.daniel.simplesql.ui.WindowAssemblyManufacturer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * This ActinListener handles the events of the ConnectionSetupWindow object.
 *
 * @author Bryan Daniel
 */
public class SetupListener implements ActionListener {

    /**
     * The combo box holding the driver selection
     */
    private final JComboBox<String> driverComboBox;

    /**
     * The input for database name
     */
    private final JTextField databaseNameTextField;

    /**
     * The input for port number
     */
    private final JTextField portTextField;

    /**
     * The input for username
     */
    private final JTextField usernameField;

    /**
     * The input for password
     */
    private final JPasswordField passwordField;

    /**
     * The label to display the test result
     */
    private final JLabel resultLabel;

    /**
     * This constructor sets all the values of the instance variables.
     *
     * @param driverComboBox the combo box
     * @param databaseNameTextField the database name input
     * @param portTextField the port number input
     * @param usernameField the username input
     * @param passwordField the password input
     * @param resultLabel the result label
     */
    public SetupListener(JComboBox<String> driverComboBox, JTextField databaseNameTextField,
            JTextField portTextField, JTextField usernameField, JPasswordField passwordField,
            JLabel resultLabel) {
        this.driverComboBox = driverComboBox;
        this.databaseNameTextField = databaseNameTextField;
        this.portTextField = portTextField;
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.resultLabel = resultLabel;
    }

    /**
     * This method coordinates the functions to handle events from the user
     * interface.
     *
     * @param e the action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        String action = e.getActionCommand();
        String driverString;
        String host;
        String databaseName;
        String port;
        String username;
        String password;
        ConnectionProperties properties;

        switch (action) {
            case TEST_COMMAND:
                resultLabel.setText("Testing connection...");
                resultLabel.repaint();

                driverString = ((String) driverComboBox.getSelectedItem())
                        .equals("MySQL") ? "jdbc:mysql://" : null;
                host = "127.0.0.1";
                databaseName = databaseNameTextField.getText();
                port = portTextField.getText();
                username = usernameField.getText();
                password = new String(passwordField.getPassword());

                try {
                    properties = new ConnectionProperties(driverString, host, databaseName,
                            port, username, password);
                    new ConnectionTestWorker(properties, resultLabel).execute();
                } catch (NullPointerException | IllegalArgumentException ex1) {
                    resultLabel.setText("You must provide all connection information!");
                    resultLabel.repaint();
                }
                break;
            case CONNECT_COMMAND:
                driverString = ((String) driverComboBox.getSelectedItem())
                        .equals("MySQL") ? "jdbc:mysql://" : null;
                host = "127.0.0.1";
                databaseName = databaseNameTextField.getText();
                port = portTextField.getText();
                username = usernameField.getText();
                password = new String(passwordField.getPassword());

                try {
                    properties = new ConnectionProperties(driverString,
                            host, databaseName, port, username, password);
                    ExecutorService executor = Executors.newFixedThreadPool(1);
                    Future<Boolean> future
                            = executor.submit(new TestQueryRunner(properties));
                    if (future.get()) {
                        resultLabel.setText("Connection good.");
                        resultLabel.repaint();
                        WindowAssemblyManufacturer factory = WindowAssemblyManufacturer.getInstance();
                        WindowAssembler assembler = factory.employWindowAssemblerForQueries(properties);
                        QueryWindow queryWindow = (QueryWindow) assembler.assembleWindow();
                        queryWindow.setLocationRelativeTo(null);
                        queryWindow.setAlwaysOnTop(true);
                        queryWindow.setVisible(true);
                        SwingUtilities.windowForComponent(resultLabel).dispose();
                    } else {
                        resultLabel.setText("Connection failed!");
                        resultLabel.repaint();
                    }
                } catch (InterruptedException | ExecutionException ex2) {
                    Logger.getLogger(SetupListener.class.getName()).log(Level.SEVERE, null, ex2);
                } catch (NullPointerException | IllegalArgumentException ex3) {
                    Logger.getLogger(SetupListener.class.getName()).log(Level.SEVERE, null, ex3);
                    resultLabel.setText("You must provide all connection information!");
                    resultLabel.repaint();
                }
                break;
            case EXIT_COMMAND:                
                System.exit(0);
                break;
            default:
                break;
        }
    }

}
