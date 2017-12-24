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
package com.daniel.simplesql;

import com.daniel.simplesql.ui.ConnectionSetupWindow;
import com.daniel.simplesql.ui.WindowAssembler;
import com.daniel.simplesql.ui.WindowAssemblyManufacturer;
import javax.swing.SwingUtilities;

/**
 * This class starts the Simple SQL Window program.
 *
 * @author Bryan Daniel
 */
public class Main {

    /**
     * The main method kicks off the program by opening the connection setup
     * window.
     *
     * @param args no arguments required
     */
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                WindowAssemblyManufacturer factory = WindowAssemblyManufacturer.getInstance();
                WindowAssembler assembler = factory.employWindowAssemblerForConnectionSetup();
                ConnectionSetupWindow setupWindow = (ConnectionSetupWindow) assembler.assembleWindow();
                setupWindow.setLocationRelativeTo(null);
                setupWindow.setVisible(true);
            }
        });
    }
}
