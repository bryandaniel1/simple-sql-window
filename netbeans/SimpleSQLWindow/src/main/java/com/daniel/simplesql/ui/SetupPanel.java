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

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * This implementation of JPanel provides a unique look and feel for the
 * connection setup window.
 *
 * @author Bryan Daniel
 */
public class SetupPanel extends JPanel {

    /**
     * Default serial version UID
     */
    private static final long serialVersionUID = -5444551418103956247L;

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
     * This overrides the paintComponent method to display a gradient color
     * background.
     *
     * @param graphics the Graphics context of this component
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics.create();
        Color beginColor = new Color(RED_VALUE, GREEN_VALUE, BLUE_VALUE);
        Color endColor = beginColor.darker();
        int width = getWidth();
        int height = getHeight();
        GradientPaint gradientPaint = new GradientPaint(
                0, 0, beginColor, 0, height, endColor);
        graphics2D.setPaint(gradientPaint);
        graphics2D.fillRect(0, 0, width, height);
        graphics2D.dispose();
    }
}
