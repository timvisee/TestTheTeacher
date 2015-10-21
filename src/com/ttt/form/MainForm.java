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
import com.ttt.util.StringUtils;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {

    /** Frame title. */
    private static final String FORM_TITLE = App.APP_NAME + " - Welkom";

    /** App instance. */
    private App app;

    /** The question label. */
    private JLabel mainLabel = new JLabel("<html><b>Welkom op onze quiz.</b>");

    /**
     * Start quiz button.
     */
    private JButton startQuizButton = new JButton("Start quiz");

    /**
     * Create quiz button.
     */
    private JButton createQuizButton = new JButton("Creëer eigen quiz");

    private JButton aboutForm = new JButton("Over");

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
//        // Construct a grid bag constraints object to specify the placement of all components
//        GridBagConstraints c = new GridBagConstraints();
//
//        // Set the frame layout
//        this.setLayout(new GridBagLayout());
//
//        // Make the frame non-resizable
//        this.setResizable(true);
//
//        // Create the main panel, to put the question and answers in
//        JPanel pnlMain = new JPanel();
//        pnlMain.setLayout(new GridBagLayout());
//
//        // Configure the placement of the questions label, and add it to the questions panel
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridx = 0;
//        c.gridy = 0;
//        c.gridwidth = 3;
//        c.insets = new Insets(0, 0, 25, 0);
//        pnlMain.add(mainLabel, c);
//
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridx = 0;
//        c.gridy = 1;
//        c.gridwidth = 1;
//        c.gridheight = 2;
//        c.insets = new Insets(0, 0, 10, 10);
//        pnlMain.add(new JLabel("Quizzen:"), c);
//
//        // Create the quiz manager list and add it to the main panel
//        JScrollPane quizList = createQuizList();
//        c.fill = GridBagConstraints.BOTH;
//        c.gridx = 1;
//        c.gridy = 1;
//        c.gridwidth = 1;
//        c.gridheight = 2;
//        c.weightx = 1.0;
//        c.weighty = 1.0;
//        c.insets = new Insets(0, 0, 0, 0);
//        pnlMain.add(quizList, c);
//
//        // Create the manage button panel
//        JPanel manageButtonPanel = createManageButtonPanel();
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridx = 2;
//        c.gridy = 1;
//        c.gridheight = 1;
//        c.weightx = 0.0;
//        c.weighty = 0.0;
//        c.insets = new Insets(0, 10, 0, 0);
//        c.anchor = GridBagConstraints.NORTH;
//        pnlMain.add(manageButtonPanel, c);
//
//        // Create the control button panel
//        JPanel controlButtonPanel = createControlButtonPanel();
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridx = 2;
//        c.gridy = 2;
//        c.weightx = 0.0;
//        c.weighty = 0.0;
//        c.insets = new Insets(10, 10, 0, 0);
//        c.anchor = GridBagConstraints.SOUTH;
//        pnlMain.add(controlButtonPanel, c);
//
//        // Configure the main panel placement and add it to the frame
//        c.fill = GridBagConstraints.BOTH;
//        c.gridx = 0;
//        c.gridy = 0;
//        c.weightx = 1.0;
//        c.weighty = 1.0;
//        c.insets = new Insets(10, 10, 10, 10);
//        this.add(pnlMain, c);










        // Create the main panel to put everything in
        JPanel mainPanel = new JPanel();

        // Set the frame layout
        mainPanel.setLayout(new GridLayout(4, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Make the frame non-resizable
        this.setResizable(true);

        // Set the window location to the system's default
        this.setLocationByPlatform(true);
        this.setLocationRelativeTo(null);

        // Configure the placement of the questions label, and add it to the questions panel
        mainPanel.add(mainLabel);

        // Add and configure the start quiz button
        mainPanel.add(startQuizButton);
        startQuizButton.addActionListener(e -> app.startQuiz());

        // Add and configure the create quiz button
        mainPanel.add(createQuizButton);
        createQuizButton.addActionListener(e -> app.showQuizManagerForm());

        mainPanel.add(aboutForm);
        aboutForm.addActionListener(e -> app.showAboutForm());

        // Add the main panel to the frame
        add(mainPanel);

        // Pack the frame
        pack();
    }
}
