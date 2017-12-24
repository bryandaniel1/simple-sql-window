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

import com.daniel.simplesql.logic.QueryWorker;
import com.daniel.simplesql.model.ConnectionProperties;
import com.daniel.simplesql.ui.ConnectionSetupWindow;
import static com.daniel.simplesql.ui.QueryWindow.CHANGE_CONNECTION_COMMAND;
import static com.daniel.simplesql.ui.QueryWindow.CLEAR_COMMAND;
import static com.daniel.simplesql.ui.QueryWindow.EXIT_COMMAND;
import static com.daniel.simplesql.ui.QueryWindow.RUN_QUERY_COMMAND;
import com.daniel.simplesql.ui.WindowAssembler;
import com.daniel.simplesql.ui.WindowAssemblyManufacturer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 *
 * @author Bryan Daniel
 */
public class QueryListener implements ActionListener {

    /**
     * The input area for queries
     */
    private final JTextArea queryTextArea;

    /**
     * The database connection properties
     */
    private final ConnectionProperties properties;

    /**
     * The tabbed pane holding results
     */
    private final JTabbedPane tabbedPane;

    /**
     * This constructor sets the values for the text area, the connection
     * properties, and the tabbed pane.
     *
     * @param queryTextArea the text area for query input
     * @param properties the database connection properties
     * @param tabbedPane the tabbed pane
     */
    public QueryListener(JTextArea queryTextArea, ConnectionProperties properties,
            JTabbedPane tabbedPane) {
        this.queryTextArea = queryTextArea;
        this.properties = properties;
        this.tabbedPane = tabbedPane;
    }

    /**
     * This method responds to the action event by determining the action
     * command and executing the appropriate process.
     *
     * @param e the ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        switch (command) {
            case EXIT_COMMAND:
                System.exit(0);
                break;
            case CHANGE_CONNECTION_COMMAND:
                WindowAssemblyManufacturer factory = WindowAssemblyManufacturer.getInstance();
                WindowAssembler assembler = factory.employWindowAssemblerForConnectionSetup();
                ConnectionSetupWindow setupWindow = (ConnectionSetupWindow) assembler.assembleWindow();
                setupWindow.setLocationRelativeTo(null);
                setupWindow.setVisible(true);
                SwingUtilities.windowForComponent(queryTextArea).dispose();
                break;
            case CLEAR_COMMAND:
                tabbedPane.removeAll();
                tabbedPane.repaint();
                break;
            case RUN_QUERY_COMMAND:
                new QueryWorker(queryTextArea.getText(), properties, tabbedPane)
                        .execute();
                break;
            default:
                break;
        }
    }

}
