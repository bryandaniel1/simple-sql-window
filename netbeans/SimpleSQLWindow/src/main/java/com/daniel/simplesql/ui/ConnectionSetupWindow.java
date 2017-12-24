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

import javax.swing.JFrame;

/**
 * The frame for collecting database connection information and starting the SQL
 * window.
 *
 * @author Bryan Daniel
 */
public class ConnectionSetupWindow extends JFrame {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = -6184959285247074703L;

    /**
     * The width of the setup window
     */
    public static final int FRAME_WIDTH = 500;

    /**
     * The height of the setup window
     */
    public static final int FRAME_HEIGHT = 500;

    /**
     * The width for user input
     */
    public static final int INPUT_WIDTH = 150;

    /**
     * The height for user input
     */
    public static final int INPUT_HEIGHT = 20;
    
    /**
     * The action command for the test button
     */
    public static final String TEST_COMMAND = "Test Connection";
    
    /**
     * The action command for the connect button
     */
    public static final String CONNECT_COMMAND = "Connect";
    
    /**
     * The action command for the exit button
     */
    public static final String EXIT_COMMAND = "Exit";

    /**
     * Default constructor
     */
    public ConnectionSetupWindow() {
        super("Setup Database Connection");
    }
}
