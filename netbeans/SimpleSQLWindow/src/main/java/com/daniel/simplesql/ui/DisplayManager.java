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

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * The Display class represents a text-based display which provides methods for
 * displaying result sets from executed queries.
 *
 * @author Bryan Daniel
 */
public class DisplayManager {

    /**
     * The print stream object
     */
    private final PrintStream displayStream;

    /**
     * Constructs a new DisplayManager and associates it with an output stream.
     *
     * @param outputStream the stream the display will write to
     */
    public DisplayManager(OutputStream outputStream) {
        displayStream = new PrintStream(outputStream);
    }

    /**
     * Displays a result line to the output stream.
     *
     * @param resultLine the result to display
     */
    public synchronized void displayResultLine(String resultLine) {
        displayStream.println("\n" + resultLine);
    }

    /**
     * Displays a result line to the output stream.
     *
     * @param resultList the result set to display
     */
    public synchronized void displayResultSet(List<String> resultList) {
        for(String row: resultList){
            displayStream.println("\n" + row);
        }        
    }
}
