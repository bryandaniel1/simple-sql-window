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

import com.daniel.simplesql.controller.QueryListener;
import com.daniel.simplesql.model.ConnectionProperties;
import static com.daniel.simplesql.ui.QueryWindow.BLUE_VALUE;
import static com.daniel.simplesql.ui.QueryWindow.BUTTONS_BORDER_TITLE;
import static com.daniel.simplesql.ui.QueryWindow.CENTER_PANEL_WIDTH;
import static com.daniel.simplesql.ui.QueryWindow.CHANGE_CONNECTION_COMMAND;
import static com.daniel.simplesql.ui.QueryWindow.CLEAR_COMMAND;
import static com.daniel.simplesql.ui.QueryWindow.EXIT_COMMAND;
import static com.daniel.simplesql.ui.QueryWindow.GREEN_VALUE;
import static com.daniel.simplesql.ui.QueryWindow.GUI_TITLE_STRING;
import static com.daniel.simplesql.ui.QueryWindow.PILLAR_WIDTH;
import static com.daniel.simplesql.ui.QueryWindow.QUERIES_TITLE;
import static com.daniel.simplesql.ui.QueryWindow.QUERY_WINDOW_HEIGHT;
import static com.daniel.simplesql.ui.QueryWindow.QUERY_WINDOW_WIDTH;
import static com.daniel.simplesql.ui.QueryWindow.RED_VALUE;
import static com.daniel.simplesql.ui.QueryWindow.RESULTS_TITLE;
import static com.daniel.simplesql.ui.QueryWindow.RUN_QUERY_COMMAND;
import static com.daniel.simplesql.ui.QueryWindow.WELCOME;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.LinearGradientPaint;
import java.awt.RadialGradientPaint;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

/**
 * This WindowAssembler creates a user interface for the execution of queries
 * and display of result sets.
 *
 * @author Bryan Daniel
 */
public class QueryWindowAssembler implements WindowAssembler {

    /**
     * The database connection properties
     */
    private final ConnectionProperties properties;

    /**
     * The input area for queries
     */
    private JTextArea queryTextArea;

    /**
     * The display area for results
     */
    private JTextArea displayTextArea;

    /**
     * The action listener for the query window
     */
    private ActionListener queryListener;

    /**
     * The constructor calls sets the value for properties.
     *
     * @param properties the properties
     */
    public QueryWindowAssembler(ConnectionProperties properties) {
        this.properties = properties;
    }

    /**
     * This assembleWindow implementation creates the look and feel of the query
     * window and assigns the action listener.
     *
     * @return the query window
     */
    @Override
    public JFrame assembleWindow() {

        queryTextArea = new JTextArea(20, 30);
        queryTextArea.setEditable(true);
        queryTextArea.setEnabled(true);
        queryTextArea.setBackground(Color.BLACK);
        queryTextArea.setForeground(Color.YELLOW);
        queryTextArea.setDisabledTextColor(Color.YELLOW);
        queryTextArea.setCaretColor(Color.YELLOW);

        ResultsPanel resultsPanel = new ResultsPanel();
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        resultsPanel.add(tabbedPane);

        displayTextArea = new JTextArea(20, 30);
        displayTextArea.setEditable(false);
        displayTextArea.setEnabled(false);
        displayTextArea.setBackground(Color.BLACK);
        displayTextArea.setForeground(Color.YELLOW);
        displayTextArea.setDisabledTextColor(Color.YELLOW);
        queryListener = new QueryListener(queryTextArea, properties, tabbedPane);

        QueryWindow queryWindow = new QueryWindow(WELCOME);
        queryWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        queryWindow.setSize(QUERY_WINDOW_WIDTH, QUERY_WINDOW_HEIGHT);
        queryWindow.setLayout(new BorderLayout());
        queryWindow.add(new TitlePanel(), BorderLayout.NORTH);
        queryWindow.add(new Pillar(), BorderLayout.WEST);
        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        centerPanel.add(new QueryPanel());
        centerPanel.add(resultsPanel);
        queryWindow.add(centerPanel, BorderLayout.CENTER);
        queryWindow.add(new Pillar(), BorderLayout.EAST);
        queryWindow.add(new ButtonPanel(), BorderLayout.SOUTH);

        return queryWindow;
    }

    /**
     * The title panel contains the name of the program.
     *
     * @author Bryan Daniel
     */
    private class TitlePanel extends JPanel {

        /**
         * Serial version UID
         */
        private static final long serialVersionUID = 1L;

        /**
         * The constructor for the title panel calls the setValues method.
         */
        private TitlePanel() {
            setValues();
        }

        /**
         * This method sets the necessary values for the query panel.
         */
        private void setValues() {
            add(new JLabel("<html><head><style>.title{text-align:center;font-size:150%;color:black;}"
                    + "</style></head><p class=\"title\">" + GUI_TITLE_STRING + "</p></html>"), JLabel.CENTER);
            TitledBorder titledBorder;
            titledBorder
                    = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                            "<html><font color=black size=+1>" + WELCOME + "</html>");
            titledBorder.setTitleJustification(TitledBorder.CENTER);
            setBorder(titledBorder);
        }

        /**
         * This overrides the paintComponent method to display a gradient color
         * background.
         * 
         * @param graphics the Graphics object
         */
        @Override
        public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D graphics2d = (Graphics2D) graphics.create();
            int width = getWidth();
            int height = getHeight();
            Color color1 = new Color(RED_VALUE, GREEN_VALUE, BLUE_VALUE);
            Color color2 = color1.darker();
            Color[] colors = {color1, color1, color2};
            float radius = width;
            Point2D center = new Point2D.Float(width/2, height/3);
            float[] fractions = {0.0f, 0.2f, 1.0f};
            RadialGradientPaint radialGradientPaint = new RadialGradientPaint(center, 
                    radius, fractions, colors);
            graphics2d.setPaint(radialGradientPaint);
            graphics2d.fillRect(0, 0, width, height);
            graphics2d.dispose();
        }
    }

    /**
     * The query panel holds the text area to take user input for queries.
     *
     * @author Bryan Daniel
     */
    private class QueryPanel extends JPanel {

        /**
         * Serial version UID
         */
        private static final long serialVersionUID = 1L;

        /**
         * The constructor for the query panel calls the setValues method.
         */
        private QueryPanel() {
            setValues();
        }

        /**
         * This method sets the necessary values for the query panel.
         */
        private void setValues() {
            setLayout(new GridLayout(1, 3));
            setPreferredSize(new Dimension(CENTER_PANEL_WIDTH, 0));
            TitledBorder titledBorder;
            titledBorder
                    = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                            "<html><font color=black size=+1>" + QUERIES_TITLE + "</html>");
            titledBorder.setTitleJustification(TitledBorder.CENTER);
            setBorder(titledBorder);
            // wrap a scrollpane around text area
            JScrollPane scrollPane = new JScrollPane(queryTextArea);
            displayTextArea.setAutoscrolls(true);
            scrollPane.setPreferredSize(new Dimension(200, 200));

            add(scrollPane);
        }

        /**
         * This overrides the paintComponent method to display a gradient color
         * background.
         * 
         * @param graphics the Graphics object
         */
        @Override
        public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D graphics2d = (Graphics2D) graphics.create();
            Color color1 = new Color(RED_VALUE, GREEN_VALUE, BLUE_VALUE);
            Color color2 = color1.darker();
            Color color3 = color2.darker();
            Color[] colors = {color3, color1, color1, color3};
            Point2D start = new Point2D.Float(0, 0);
            Point2D end = new Point2D.Float(CENTER_PANEL_WIDTH, 0);
            float[] fractions = {0.0f, 0.4f, 0.6f, 1.0f};
            int width = getWidth();
            int height = getHeight();
            LinearGradientPaint linearGradientPaint = new LinearGradientPaint(start, 
                    end, fractions, colors);
            graphics2d.setPaint(linearGradientPaint);
            graphics2d.fillRect(0, 0, width, height);
            graphics2d.dispose();
        }
    }

    /**
     * The results panel holds the text area to display result sets to the user.
     *
     * @author Bryan Daniel
     */
    public class ResultsPanel extends JPanel {

        /**
         * Serial version UID
         */
        private static final long serialVersionUID = 1L;

        /**
         * The constructor for the results panel calls the setValues method.
         */
        private ResultsPanel() {
            setValues();
        }

        /**
         * This method sets the necessary values for the results panel.
         */
        private void setValues() {
            setLayout(new GridLayout(1, 1));
            setPreferredSize(new Dimension(CENTER_PANEL_WIDTH, 0));
            TitledBorder titledBorder;
            titledBorder
                    = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                            "<html><font color=black size=+1>" + RESULTS_TITLE + "</html>");
            titledBorder.setTitleJustification(TitledBorder.CENTER);
            setBorder(titledBorder);
        }

        /**
         * This overrides the paintComponent method to display a gradient color
         * background.
         * 
         * @param graphics the Graphics object
         */
        @Override
        public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D graphics2d = (Graphics2D) graphics.create();
            Color color1 = new Color(RED_VALUE, GREEN_VALUE, BLUE_VALUE);
            Color color2 = color1.darker();
            Color color3 = color2.darker();
            Color[] colors = {color3, color1, color1, color3};
            Point2D start = new Point2D.Float(0, 0);
            Point2D end = new Point2D.Float(CENTER_PANEL_WIDTH, 0);
            float[] fractions = {0.0f, 0.4f, 0.6f, 1.0f};
            int width = getWidth();
            int height = getHeight();
            LinearGradientPaint linearGradientPaint = new LinearGradientPaint(start, 
                    end, fractions, colors);
            graphics2d.setPaint(linearGradientPaint);
            graphics2d.fillRect(0, 0, width, height);
            graphics2d.dispose();
        }
    }

    /**
     * This class resembles a cylindrical pillar supporting the title panel.
     *
     * @author Bryan Daniel
     */
    private class Pillar extends JPanel {

        /**
         * Serial version UID
         */
        private static final long serialVersionUID = 1L;

        /**
         * The Pillar constructor calls the setValues method.
         */
        private Pillar() {
            setValues();
        }

        /**
         * This method sets the necessary values for the pillar.
         */
        private void setValues() {
            setPreferredSize(new Dimension(PILLAR_WIDTH, 0));
        }

        /**
         * This overrides the paintComponent method to display a gradient color
         * background.
         * 
         * @param graphics the Graphics object
         */
        @Override
        public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D graphics2d = (Graphics2D) graphics.create();
            Color color1 = new Color(RED_VALUE, GREEN_VALUE, BLUE_VALUE);
            Color color2 = color1.darker();
            Color[] colors = {color2, color1, color2};
            Point2D start = new Point2D.Float(0, 0);
            Point2D end = new Point2D.Float(PILLAR_WIDTH, 0);
            float[] fractions = {0.0f, 0.4f, 1.0f};
            int width = getWidth();
            int height = getHeight();
            LinearGradientPaint linearGradientPaint = new LinearGradientPaint(start, 
                    end, fractions, colors);
            graphics2d.setPaint(linearGradientPaint);
            graphics2d.fillRect(0, 0, width, height);
            graphics2d.dispose();
        }
    }

    /**
     * The button panel holds the 4 buttons corresponding to the program
     * functions of program exit, changing connection, clearing results, and
     * running a query.
     *
     * @author Bryan Daniel
     */
    private class ButtonPanel extends JPanel {

        /**
         * Serial version UID
         */
        private static final long serialVersionUID = 1L;

        /**
         * The ButtonPanel constructor calls the setValues method.
         */
        private ButtonPanel() {
            setValues();
        }

        /**
         * This method sets the necessary values for the button panel.
         */
        private void setValues() {
            setLayout(new GridLayout(1, 4));
            TitledBorder titledBorder;
            titledBorder
                    = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                            "<html><font color=black size=+1>" + BUTTONS_BORDER_TITLE + "</html>");
            titledBorder.setTitleJustification(TitledBorder.CENTER);
            setBorder(titledBorder);

            //The exit button
            JButton exitButton = new QueryWindowButton(EXIT_COMMAND);

            //The button to change the connection
            JButton changeConnectionButton = new QueryWindowButton(CHANGE_CONNECTION_COMMAND);

            //The button to clear results
            JButton fileAndDbButton = new QueryWindowButton(CLEAR_COMMAND);

            //The button to execute the statements in the input window
            JButton queryButton = new QueryWindowButton(RUN_QUERY_COMMAND);

            //GroupLayout is declared.
            GroupLayout buttonGroup = new GroupLayout(this);
            this.setLayout(buttonGroup);

            //Creating gaps
            buttonGroup.setAutoCreateGaps(true);
            buttonGroup.setAutoCreateContainerGaps(true);

            //These statements set up the horizontal and vertical grouping for the panel
            buttonGroup.setHorizontalGroup(buttonGroup.createSequentialGroup().addComponent(exitButton).addComponent(changeConnectionButton)
                    .addComponent(fileAndDbButton).addComponent(queryButton).addGroup(buttonGroup.createParallelGroup(GroupLayout.Alignment.CENTER)));

            buttonGroup.setVerticalGroup(buttonGroup.createSequentialGroup().addGroup(buttonGroup.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(exitButton).addComponent(changeConnectionButton).addComponent(fileAndDbButton).addComponent(queryButton)));

            buttonGroup.linkSize(SwingConstants.HORIZONTAL, exitButton, changeConnectionButton, fileAndDbButton, queryButton);
            buttonGroup.linkSize(SwingConstants.VERTICAL, exitButton, changeConnectionButton, fileAndDbButton, queryButton);

            add(exitButton);
            add(changeConnectionButton);
            add(fileAndDbButton);
            add(queryButton);
        }

        /**
         * This overrides the paintComponent method to display a gradient color
         * background.
         * 
         * @param graphics the Graphics object
         */
        @Override
        public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D graphics2d = (Graphics2D) graphics.create();
            Color color1 = new Color(RED_VALUE, GREEN_VALUE, BLUE_VALUE);
            Color color2 = color1.darker();
            int width = getWidth();
            int height = getHeight();
            GradientPaint gradientPaint = new GradientPaint(
                    0, 0, color1, 0, height, color2);
            graphics2d.setPaint(gradientPaint);
            graphics2d.fillRect(0, 0, width, height);
            graphics2d.dispose();
        }
    }

    /**
     * This class defines the buttons in the query window button panel.
     *
     * @author Bryan Daniel
     */
    private class QueryWindowButton extends JButton {

        /**
         * Constants
         */
        private static final long serialVersionUID = 1L;

        /**
         * The QueryWindowButton constructor calls the setValues method.
         *
         * @param text the button text
         */
        private QueryWindowButton(String text) {
            setValues(text);
        }

        /**
         * This method sets the necessary values for the button.
         *
         * @param text the button text
         */
        private void setValues(String text) {
            setText("<html><head><style>.button{text-align:center;color:black;}</style></head>"
                    + "<p class=\"button\">" + text + "<p></html>");

            setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED, Color.WHITE, Color.BLACK));
            setMaximumSize(new Dimension(160, 100));
            setActionCommand(text);
            addActionListener(queryListener);
        }
    }
}
