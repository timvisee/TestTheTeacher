/**
 * TestTheTeacher.
 * Test the teacher to see whether he or she is really good enough at basing programming.
 * A project for a school of applied sciences.
 *
 * @author MingYan Li, Tim Visee
 * @copyright Copyright (c) MingYan Li & Tim Visee 2015. All rights reserved.
 * @license GNU GPL v3.0
 */

package com.ttt.form;

import com.ttt.App;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {

    /** Frame title. */
    private static final String FORM_TITLE = App.APP_NAME + " - Welcome";

    /** App instance. */
    private App app;

    /** The question label. */
    private JLabel mainLabel = new JLabel("<html><b>Welcome to our awesome quiz app!</b>");

    /**
     * Constructor.
     *
     * @param app App instance.
     * @param show True to show the frame once it has been initialized.
     */
    public MainForm(App app, boolean show) {
        // Construct the form
        super(FORM_TITLE);

        // Store the app instance
        this.app = app;

        // Create the form UI
        createUIComponents();

        // Close application when closing form
        // TODO: Should we keep this?
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Show the form
        this.setVisible(show);
    }

    /**
     * Create all UI components for the frame.
     */
    private void createUIComponents() {
        // Construct a grid bag constraints object to specify the placement of all components
        GridBagConstraints c = new GridBagConstraints();

        // Set the frame layout
        this.setLayout(new GridBagLayout());

        // Set the frame size
        // TODO: Set proper size here!
        //this.setSize(400, 215);

        // Make the frame non-resizable
        this.setResizable(false);

        // Set the window location to the system's default
        this.setLocationByPlatform(true);
        this.setLocationRelativeTo(null);

        // Configure the placement of the questions label, and add it to the questions panel
        add(mainLabel, c);

        // TODO: Add a button to start the main quiz.

        // TODO: Add a button to create custom quizzes.

        // Pack the frame
        pack();
    }
}
