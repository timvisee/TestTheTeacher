/**
 * TestTheTeacher.
 * Test the teacher to see whether he or she is really good enough at basing programming.
 * A project for a school of applied sciences.
 *
 * @author MingYan Li, Tim Visee
 * @copyright Copyright (c) MingYan Li & Tim Visee 2015. All rights reserved.
 * @license GNU GPL v3.0
 */

package com.ttt.form.component;

import javax.swing.*;
import java.awt.*;

public class TTTLabel extends JLabel {

    /**
     * Constructor.
     *
     * @param text Label text.
     */
    public TTTLabel(String text) {
        super(text);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        d.setSize(d.getWidth(), 500);
        return d;
    }

    @Override
    public Dimension getMaximumSize() {
        Dimension d = super.getPreferredSize();
        d.setSize(d.getWidth(), 500);
        return d;
    }

    @Override
    public Dimension getMinimumSize() {
        Dimension d = super.getPreferredSize();
        d.setSize(d.getWidth(), 500);
        return d;
    }
}
