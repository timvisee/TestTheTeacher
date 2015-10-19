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
     * Start quiz button.
     */
    private JButton startQuizButton = new JButton("Start quiz");

    /**
     * Create quiz button.
     */
    private JButton createQuizButton = new JButton("Create quiz");

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
        // Create the main panel to put everything in
        JPanel mainPanel = new JPanel();

        // Set the frame layout
        mainPanel.setLayout(new GridLayout(3, 1, 5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // Make the frame non-resizable
        this.setResizable(true);

        // Set the window location to the system's default
        this.setLocationByPlatform(true);
        this.setLocationRelativeTo(null);

        // Configure the placement of the questions label, and add it to the questions panel
        mainPanel.add(mainLabel);

        // Add and configure the start quiz button
        mainPanel.add(startQuizButton);
        startQuizButton.addActionListener(e -> app.showQuestionForm());

        // Add and configure the create quiz button
        mainPanel.add(createQuizButton);

        // Add the main panel to the frame
        add(mainPanel);

        // Pack the frame
        pack();
    }

}
