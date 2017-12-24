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
package com.daniel.simplesql.ui;

import com.daniel.simplesql.controller.SetupListener;
import static com.daniel.simplesql.ui.ConnectionSetupWindow.CONNECT_COMMAND;
import static com.daniel.simplesql.ui.ConnectionSetupWindow.EXIT_COMMAND;
import static com.daniel.simplesql.ui.ConnectionSetupWindow.FRAME_HEIGHT;
import static com.daniel.simplesql.ui.ConnectionSetupWindow.FRAME_WIDTH;
import static com.daniel.simplesql.ui.ConnectionSetupWindow.INPUT_HEIGHT;
import static com.daniel.simplesql.ui.ConnectionSetupWindow.INPUT_WIDTH;
import static com.daniel.simplesql.ui.ConnectionSetupWindow.TEST_COMMAND;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

/**
 * This WindowAssembler creates a user interface for database connection setup.
 *
 * @author Bryan Daniel
 */
public class ConnectionSetupWindowAssembler implements WindowAssembler {

    /**
     * This implementation of assembleWindow creates a user interface for
     * collecting database connection information.
     *
     * @return the connection setup window
     */
    @Override
    public JFrame assembleWindow() {
        ConnectionSetupWindow setupWindow = new ConnectionSetupWindow();
        setupWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setupWindow.setLayout(new GridLayout(5, 1));

        GridBagConstraints center = new GridBagConstraints();
        center.anchor = GridBagConstraints.CENTER;
        center.fill = GridBagConstraints.NONE;

        // driver
        JPanel driverSelect = new SetupPanel();
        driverSelect.setLayout(new GridLayout(1, 2));

        // driver label
        driverSelect.add(new JLabel("Select Driver:", SwingConstants.RIGHT));

        // driver selection
        JPanel driverComboBoxPanel = new SetupPanel();
        driverComboBoxPanel.setLayout(new GridBagLayout());
        String[] driverSelections = new String[]{"Select driver", "MySQL"};
        JComboBox<String> driverComboBox = new JComboBox<>(driverSelections);
        driverComboBox.setPreferredSize(new Dimension(INPUT_WIDTH, INPUT_HEIGHT));
        driverComboBoxPanel.add(driverComboBox, center);
        driverSelect.add(driverComboBoxPanel);

        // database name
        JPanel databaseNameInput = new SetupPanel();
        databaseNameInput.setLayout(new GridLayout(1, 2));

        // database name label
        databaseNameInput.add(new JLabel("Enter Database Name:", SwingConstants.RIGHT));

        // database name input
        JPanel databaseNameTextFieldPanel = new SetupPanel();
        databaseNameTextFieldPanel.setLayout(new GridBagLayout());
        JTextField databaseNameTextField = new JTextField();
        databaseNameTextField.setPreferredSize(new Dimension(INPUT_WIDTH, INPUT_HEIGHT));
        databaseNameTextFieldPanel.add(databaseNameTextField, center);
        databaseNameInput.add(databaseNameTextFieldPanel);

        // port number
        JPanel portInput = new SetupPanel();
        portInput.setLayout(new GridLayout(1, 2));

        // port number label
        portInput.add(new JLabel("Enter Port Number:", SwingConstants.RIGHT));

        // port number input
        JPanel portTextFieldPanel = new SetupPanel();
        portTextFieldPanel.setLayout(new GridBagLayout());
        JTextField portTextField = new JTextField();
        portTextField.setPreferredSize(new Dimension(INPUT_WIDTH, INPUT_HEIGHT));
        portTextFieldPanel.add(portTextField);
        portInput.add(portTextFieldPanel);

        // credentials
        JPanel credentialsInput = new SetupPanel();
        credentialsInput.setLayout(new GridLayout(2, 2));

        // ID label
        credentialsInput.add(new JLabel("Enter Username:", SwingConstants.RIGHT));

        // ID input
        JPanel usernameFieldPanel = new JPanel();
        usernameFieldPanel.setOpaque(false);
        usernameFieldPanel.setLayout(new GridBagLayout());
        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(INPUT_WIDTH, INPUT_HEIGHT));
        usernameFieldPanel.add(usernameField);
        credentialsInput.add(usernameFieldPanel);

        // password label
        credentialsInput.add(new JLabel("Enter Password:", SwingConstants.RIGHT));

        // password input
        JPanel passwordFieldPanel = new JPanel();
        passwordFieldPanel.setOpaque(false);
        passwordFieldPanel.setLayout(new GridBagLayout());
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(INPUT_WIDTH, INPUT_HEIGHT));
        passwordFieldPanel.add(passwordField);
        credentialsInput.add(passwordFieldPanel);

        JPanel buttonPanel = new SetupPanel();
        buttonPanel.setLayout(new GridLayout(2, 1));
        TitledBorder titledBorder;
        titledBorder
                = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                        "<html><font color=black size=+1>options</html>");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        buttonPanel.setBorder(titledBorder);

        //The test button
        JPanel testButtonPanel = new JPanel();
        testButtonPanel.setOpaque(false);
        testButtonPanel.setLayout(new GridLayout(1, 2));
        JLabel resultLabel = new JLabel();        
        JButton testConnectionButton = new JButton(TEST_COMMAND);
        SetupListener listener = new SetupListener(driverComboBox,
                databaseNameTextField, portTextField, usernameField, passwordField,
                resultLabel);
        testConnectionButton.addActionListener(listener);

        //GroupLayout is declared.
        GroupLayout testButtonGroup = new GroupLayout(testButtonPanel);
        testButtonPanel.setLayout(testButtonGroup);

        //Creating gaps
        testButtonGroup.setAutoCreateGaps(true);
        testButtonGroup.setAutoCreateContainerGaps(true);

        //These statements set up the horizontal and vertical grouping for the panel
        testButtonGroup.setHorizontalGroup(testButtonGroup.createSequentialGroup()
                .addComponent(testConnectionButton).addComponent(resultLabel)
                .addGroup(testButtonGroup.createParallelGroup(GroupLayout.Alignment.CENTER)));

        testButtonGroup.setVerticalGroup(testButtonGroup.createSequentialGroup()
                .addGroup(testButtonGroup.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(testConnectionButton).addComponent(resultLabel)));

        testButtonGroup.linkSize(SwingConstants.HORIZONTAL, testConnectionButton);
        testButtonGroup.linkSize(SwingConstants.VERTICAL, testConnectionButton);
        
        //The exit and connect buttons
        JPanel finalButtonPanel = new JPanel();
        finalButtonPanel.setOpaque(false);
        finalButtonPanel.setLayout(new GridLayout(1, 2));
        
        //The exit button
        JButton exitButton = new JButton(EXIT_COMMAND);
        exitButton.addActionListener(listener);
        
        //The connect button
        JButton connectButton = new JButton(CONNECT_COMMAND);
        connectButton.addActionListener(listener);
        
        //GroupLayout is declared.
        GroupLayout finalButtonGroup = new GroupLayout(finalButtonPanel);
        finalButtonPanel.setLayout(finalButtonGroup);

        //Creating gaps
        finalButtonGroup.setAutoCreateGaps(true);
        finalButtonGroup.setAutoCreateContainerGaps(true);

        //These statements set up the horizontal and vertical grouping for the panel
        finalButtonGroup.setHorizontalGroup(finalButtonGroup.createSequentialGroup()
                .addComponent(exitButton).addComponent(connectButton)
                .addGroup(finalButtonGroup.createParallelGroup(GroupLayout.Alignment.CENTER)));

        finalButtonGroup.setVerticalGroup(finalButtonGroup.createSequentialGroup()
                .addGroup(finalButtonGroup.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(exitButton).addComponent(connectButton)));

        finalButtonGroup.linkSize(SwingConstants.HORIZONTAL, exitButton, connectButton);
        finalButtonGroup.linkSize(SwingConstants.VERTICAL, exitButton, connectButton);
        
        buttonPanel.add(testButtonPanel);
        buttonPanel.add(finalButtonPanel);

        setupWindow.add(driverSelect);
        setupWindow.add(databaseNameInput);
        setupWindow.add(portInput);
        setupWindow.add(credentialsInput);
        setupWindow.add(buttonPanel);

        setupWindow.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        return setupWindow;
    }
}
