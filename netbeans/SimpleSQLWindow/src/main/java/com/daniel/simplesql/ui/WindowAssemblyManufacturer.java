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

import com.daniel.simplesql.model.ConnectionProperties;

/**
 * This class creates assemblers for various user interface windows. The class
 * implements the Singleton design pattern, as it is not necessary to have more
 * than one WindowAssemblyManufacturer.
 *
 * @author Bryan Daniel
 */
public class WindowAssemblyManufacturer {

    /**
     * The single WindowAssemblyManufacturer object
     */
    private static WindowAssemblyManufacturer factory = null;

    /**
     * Private constructor - not called publicly
     */
    private WindowAssemblyManufacturer() {
    }

    /**
     * This method returns the single WindowAssemblyManufacturer. If the
     * instance of WindowAssemblyManufacturer has not been created previously,
     * it is created here.
     *
     * @return the WindowAssemblyManufacturer object
     */
    public synchronized static WindowAssemblyManufacturer getInstance() {
        if (factory == null) {
            factory = new WindowAssemblyManufacturer();
        }
        return factory;
    }

    /**
     * This method returns a window assembler for a database connection setup
     * user interface.
     *
     * @return the connection setup window assembler
     */
    public WindowAssembler employWindowAssemblerForConnectionSetup() {
        return new ConnectionSetupWindowAssembler();
    }

    /**
     * This method returns a window assembler for a query development user
     * interface.
     *
     * @param properties the database connection properties for the query window
     * @return the query window assembler
     */
    public WindowAssembler employWindowAssemblerForQueries(ConnectionProperties properties) {
        return new QueryWindowAssembler(properties);
    }
}
