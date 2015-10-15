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

    /** A fixed JLabel width. */
    private int fixedWidth = 0;
    /** A fixed JLabel height. */
    private int fixedHeight = 0;
    /** Resize the width if the component width is larger than the fixed width. */
    private boolean resizeWidthIfLarger = false;
    /** Resize the height if the component height is larger than the fixed height. */
    private boolean resizeHeightIfLarger = false;

    /**
     * Constructor.
     *
     * @param text Label text.
     */
    public TTTLabel(String text) {
        super(text);
    }

    /**
     * Get the fixed label width.
     *
     * @return Fixed label width.
     */
    public int getFixedWidth() {
        return this.fixedWidth;
    }

    /**
     * Check whether the label has a fixed width.
     *
     * @return True if the label has a fixed width.
     */
    public boolean hasFixedWidth() {
        return getFixedWidth() != 0;
    }

    /**
     * Check whether the width of the label resizes if the label's width is larger than the fixed width.
     *
     * @return True if resizes, false if not.
     */
    public boolean isResizeWidthIfLarger() {
        return resizeWidthIfLarger;
    }

    /**
     * Set whether the width of the label resizes if the label's width is larger than the fixed width.
     *
     * @param resizeWidthIfLarger True to resize, false otherwise.
     */
    public void setResizeWidthIfLarger(boolean resizeWidthIfLarger) {
        this.resizeWidthIfLarger = resizeWidthIfLarger;
    }

    /**
     * Check whether the height of the label resizes if the label's height is larger than the fixed height.
     *
     * @return True if resizes, false if not.
     */
    public boolean isResizeHeightIfLarger() {
        return resizeHeightIfLarger;
    }

    /**
     * Set whether the height of the label resizes if the label's height is larger than the fixed height.
     *
     * @param resizeHeightIfLarger True to resize, false otherwise.
     */
    public void setResizeHeightIfLarger(boolean resizeHeightIfLarger) {
        this.resizeHeightIfLarger = resizeHeightIfLarger;
    }

    /**
     * Set the fixed width of the label. Zero to reset the fixed width.
     *
     * @param fixedWidth Fixed width.
     */
    public void setFixedWidth(int fixedWidth) {
        this.fixedWidth = fixedWidth;
    }

    /**
     * Reset the fixed width of the label.
     */
    public void resetFixedWidth() {
        setFixedWidth(0);
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
        d.setSize(
                !hasFixedWidth() || (getFixedWidth() < d.getWidth() && resizeWidthIfLarger) ? d.getWidth() : getFixedWidth(),
                !hasFixedHeight() || (getFixedHeight() < d.getHeight() && resizeHeightIfLarger) ? d.getHeight() : getFixedHeight()
        );
        return d;
    }

    @Override
    public Dimension getMaximumSize() {
        Dimension d = super.getPreferredSize();
        d.setSize(
                !hasFixedWidth() || (getFixedWidth() < d.getWidth() && resizeWidthIfLarger) ? d.getWidth() : getFixedWidth(),
                !hasFixedHeight() || (getFixedHeight() < d.getHeight() && resizeHeightIfLarger) ? d.getHeight() : getFixedHeight()
        );
        return d;
    }

    @Override
    public Dimension getMinimumSize() {
        Dimension d = super.getPreferredSize();
        d.setSize(
                !hasFixedWidth() || (getFixedWidth() < d.getWidth() && resizeWidthIfLarger) ? d.getWidth() : getFixedWidth(),
                !hasFixedHeight() || (getFixedHeight() < d.getHeight() && resizeHeightIfLarger) ? d.getHeight() : getFixedHeight()
        );
        return d;
    }
}
