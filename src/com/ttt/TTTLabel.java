package com.ttt;

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
