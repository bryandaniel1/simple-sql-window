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
package com.daniel.simplesql.util;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * This utility class contains functionality to modify result table
 * presentation.
 *
 * @author Bryan Daniel
 */
public class TableUtil {

    /**
     * This utility method adjusts the column widths of the given table to fit
     * the largest data element in each column.
     *
     * @param table the result table
     */
    public static void adjustTableColumnWidths(JTable table) {

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int columnCount = 0; columnCount < table.getColumnCount(); columnCount++) {
            DefaultTableColumnModel columnModel = (DefaultTableColumnModel) table.getColumnModel();
            TableColumn column = columnModel.getColumn(columnCount);
            
            // preferred width is wide enough for table header here
            int preferredColumnWidth = column.getPreferredWidth();

            // if data is wider than the header, the preferred width is expanded
            for (int rowCount = 0; rowCount < table.getRowCount(); rowCount++) {
                TableCellRenderer renderer = table.getCellRenderer(rowCount, columnCount);
                Component component = renderer.getTableCellRendererComponent(table, 
                        table.getValueAt(rowCount, columnCount), false, false, rowCount, columnCount);
                preferredColumnWidth = Math.max(preferredColumnWidth, component.getPreferredSize().width);
            }
            column.setPreferredWidth(preferredColumnWidth + 2);
        }
    }
}
