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
import com.ttt.quiz.Quiz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainForm extends JFrame {

    /** Frame title. */
    private static final String FORM_TITLE = App.APP_NAME + " - Welkom";

    /** App instance. */
    private App app;

    /** The question label. */
    private JLabel mainLabel = new JLabel(
            "<html><b>Welkom bij Test The Teacher.</b><br />" +
            "<br />" +
            "Deze applicatie is ontwikkeld om jezelf te testen<br />en waarbij je je eigen quizzes kan maken.<br />" +
            "<br />" +
            "Om je eignen quiz te maken, dien je eerst de<br />onderstaande quiz met ee nvoldoende te<br />behalen (55% of hoger).<br />" +
            "<br />" +
            "Veel plezier!");

    /**
     * Start quiz button.
     */
    private JButton startQuizButton = new JButton("Start quiz");

    /**
     * About button.
     */
    private JButton aboutButton = new JButton("Over");

    /**
     * Quiz list model instance.
     */
    private DefaultListModel<Quiz> quizListModel;

    /**
     * Quiz list instance.
     */
    private JList quizList;

    /**
     * Start button instance
     */
    private JButton startButton;

    /**
     * Create button instance.
     */
    private JButton createButton;

    /**
     * Edit button instance.
     */
    private JButton manageButton;

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
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Set the frame sizes
        this.setMinimumSize(new Dimension(500, 315));
        this.setPreferredSize(new Dimension(610, 315));
        this.setSize(new Dimension(610, 315));

        // Set the window location to the system's default
        this.setLocationByPlatform(true);
        this.setLocationRelativeTo(null);

        // Show the form
        this.setVisible(show);

        // Update the buttons
        updateButtons();
    }

    /**
     * Create all UI components for the frame.
     */
    private void createUIComponents() {
        // Construct a grid bag constraints object to specify the placement of all components
        GridBagConstraints c = new GridBagConstraints();

        // Set the frame layout
        this.setLayout(new GridBagLayout());

        // Make the frame non-resizable
        this.setResizable(true);

        // Create the main panel, to put the question and answers in
        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new GridBagLayout());

        // Configure the placement of the questions label, and add it to the questions panel
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 0, 45);
        pnlMain.add(mainLabel, c);

        // Create the quiz manager list and add it to the main panel
        JScrollPane quizList = createQuizList();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 2;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.insets = new Insets(0, 0, 0, 0);
        pnlMain.add(quizList, c);

        // Create the manage button panel
        JPanel manageButtonPanel = createManageButtonPanel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        c.gridheight = 1;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.insets = new Insets(0, 10, 0, 0);
        c.anchor = GridBagConstraints.NORTH;
        pnlMain.add(manageButtonPanel, c);

        // Create the control button panel
        JPanel controlButtonPanel = createControlButtonPanel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 2;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.insets = new Insets(0, 0, 0, 45);
        c.anchor = GridBagConstraints.SOUTH;
        pnlMain.add(controlButtonPanel, c);

        // Configure the main panel placement and add it to the frame
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.insets = new Insets(10, 10, 10, 10);
        this.add(pnlMain, c);

        // Pack the frame
        pack();
    }

    /**
     * Create a list to manage the quizzes in.
     *
     * @return Scroll pane with list.
     */
    public JScrollPane createQuizList() {
        // Create the default list model
        this.quizListModel = new DefaultListModel<>();

        // Refresh the list of quizzes to add them to the list model
        refreshList();

        // Create the list and create an empty border
        this.quizList = new JList<>(this.quizListModel);
        this.quizList.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Update the button panel on selection change
        this.quizList.addListSelectionListener(e -> updateButtons());
        this.quizList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if(evt.getClickCount() == 2) {
                    startSelectedQuiz();
                }
            }
        });

        // Create a scroll pane with the quiz list and return it
        return new JScrollPane(this.quizList);
    }

    /**
     * Create the button panel to manage the quizzes.
     *
     * @return Button panel.
     */
    public JPanel createManageButtonPanel() {
        // Create a panel to put the buttons in and set it's layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));

        // Create the buttons to add to the panel
        this.startButton = new JButton("Start");
        this.createButton = new JButton("<html>Cre&euml;er");
        this.manageButton = new JButton("Beheren");

        // Add the buttons to the panel
        buttonPanel.add(startButton);
        buttonPanel.add(createButton);
        buttonPanel.add(manageButton);
        startButton.addActionListener(e -> startSelectedQuiz());
        createButton.addActionListener(e -> app.showQuizManagerForm(true));
        manageButton.addActionListener(e -> app.showQuizManagerForm(false));

        // Return the button panel
        return buttonPanel;
    }

    /**
     * Create the button panel to control the form.
     *
     * @return Button panel.
     */
    public JPanel createControlButtonPanel() {
        // Create a panel to put the buttons in and set it's layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));

        // Add and configure the start quiz button
        buttonPanel.add(startQuizButton);
        startQuizButton.addActionListener(e -> {
            // Set the main quiz
            app.setCurrentQuiz(app.getMainQuiz());

            // Start the quiz
            app.startQuiz();
        });

        buttonPanel.add(aboutButton);
        aboutButton.addActionListener(e -> app.showAboutForm());

        // Return the button panel
        return buttonPanel;
    }

    /**
     * Refresh the list of quizzes.
     */
    public void refreshList() {
        // Clear all current items
        this.quizListModel.clear();

        // Add the items
        this.app.getQuizManager().getQuizzes().forEach(this.quizListModel::addElement);
    }

    /**
     * Update the state of buttons in the button panel.
     */
    public void updateButtons() {
        // Get the number of selected items
        int selected = getSelectedCount();

        // Enable the buttons if the quiz is completed, enable the start button if a quiz is selected
        this.startButton.setEnabled(this.app.hasCompletedQuiz() && selected == 1);
        this.createButton.setEnabled(this.app.hasCompletedQuiz());
        this.manageButton.setEnabled(this.app.hasCompletedQuiz());
        this.quizList.setEnabled(this.app.hasCompletedQuiz());
    }

    /**
     * Get the number of selected items.
     *
     * @return Number of selected items.
     */
    public int getSelectedCount() {
        return this.quizList.getSelectedValuesList().size();
    }

    /**
     * Edit the selected quiz.
     */
    public void startSelectedQuiz() {
        // Make sure just one item is selected
        if(getSelectedCount() != 1)
            return;

        // Get the selected quiz
        Quiz selected = (Quiz) this.quizList.getSelectedValue();

        // Set the current quiz
        this.app.setCurrentQuiz(selected);

        // Start the quiz
        this.app.startQuiz();
    }
}
