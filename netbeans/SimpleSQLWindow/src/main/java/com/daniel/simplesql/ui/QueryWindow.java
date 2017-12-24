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
 * This class is the JFrame for database interaction.
 *
 * @author Bryan Daniel
 */
public class QueryWindow extends JFrame {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = -379355160410888700L;
    
    /**
     * The window width
     */
    public static final int QUERY_WINDOW_WIDTH = 1200;
    
    /**
     * The window height
     */
    public static final int QUERY_WINDOW_HEIGHT = 900;
    
    /**
     * The width for the pillars
     */
    public static final int PILLAR_WIDTH = 100;
    
    /**
     * The width for the center panel
     */
    public static final int CENTER_PANEL_WIDTH = 1000;
    
    /**
     * Defines the intensity of the red color
     */
    public static final int RED_VALUE = 73;
    
    /**
     * Defines the intensity of the green color
     */
    public static final int GREEN_VALUE = 172;
    
    /**
     * Defines the intensity of the blue color
     */
    public static final int BLUE_VALUE = 221;
    
    /**
     * The window greeting
     */
    public static final String WELCOME = "Welcome to Simple SQL";
    
    /**
     * The window title
     */
    public static final String GUI_TITLE_STRING = "Simple SQL Window";
    
    /**
     * The results title
     */
    public static final String RESULTS_TITLE = "Results";
    
    /**
     * The queries title
     */
    public static final String QUERIES_TITLE = "Enter Query";
    
    /**
     * The buttons title
     */
    public static final String BUTTONS_BORDER_TITLE = "Operations";
    
    /**
     * The exit command
     */
    public static final String EXIT_COMMAND = "Exit";
    
    /**
     * The change-connection command
     */
    public static final String CHANGE_CONNECTION_COMMAND = "Change Connection";
    
    /**
     * The clear-results command
     */
    public static final String CLEAR_COMMAND = "Clear Results";
    
    /**
     * The run command
     */
    public static final String RUN_QUERY_COMMAND = "Run Query";

    /**
     * The constructor calls the super constructor.
     *
     * @param title the window title
     */
    public QueryWindow(String title) {
        super(title);
    }
}
