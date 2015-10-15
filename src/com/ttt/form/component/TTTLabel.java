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

    /** A fixed JLabel height. */
    private int fixedHeight = 0;

    /**
     * Constructor.
     *
     * @param text Label text.
     */
    public TTTLabel(String text) {
        super(text);
    }

    /**
     * Get the fixed label height.
     *
     * @return Fixed label height.
     */
    public int getFixedHeight() {
        return this.fixedHeight;
    }

    /**
     * Check whether the label has a fixed height.
     *
     * @return True if the label has a fixed height.
     */
    public boolean hasFixedHeight() {
        return getFixedHeight() != 0;
    }

    /**
     * Set the fixed height of the label. Zero to reset the fixed height.
     *
     * @param fixedHeight Fixed height.
     */
    public void setFixedHeight(int fixedHeight) {
        this.fixedHeight = fixedHeight;
    }

    /**
     * Reset the fixed height of the label.
     */
    public void resetFixedHeight() {
        setFixedHeight(0);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        d.setSize(d.getWidth(), hasFixedHeight() ? getFixedHeight() : d.getHeight());
        return d;
    }

    @Override
    public Dimension getMaximumSize() {
        Dimension d = super.getPreferredSize();
        d.setSize(d.getWidth(), hasFixedHeight() ? getFixedHeight() : d.getHeight());
        return d;
    }

    @Override
    public Dimension getMinimumSize() {
        Dimension d = super.getPreferredSize();
        d.setSize(d.getWidth(), hasFixedHeight() ? getFixedHeight() : d.getHeight());
        return d;
    }
}
