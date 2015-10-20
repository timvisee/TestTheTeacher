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
import java.util.ArrayList;
import java.util.List;

public class QuizManagerForm extends JDialog {

    /** Frame title. */
    private static final String FORM_TITLE = App.APP_NAME + " - Quiz Manager";

    /** App instance. */
    private App app;

    /** The quiz manager label. */
    private JLabel mainLabel = new JLabel("<html>Create, manage or delete a custom quiz using the buttons on the side.");

    /**
     * Quiz list model instance.
     */
    private DefaultListModel<Quiz> quizListModel;

    /**
     * Quiz list instance.
     */
    private JList quizList;

    /**
     * Create button instance.
     */
    private JButton createButton;

    /**
     * Edit button instance.
     */
    private JButton editButton;

    /**
     * Move up button instance.
     */
    private JButton moveUpButton;

    /**
     * Move down button instance.
     */
    private JButton moveDownButton;

    /**
     * Delete button instance.
     */
    private JButton deleteButton;

    /**
     * List of the quizzes being shown.
     */
    private List<Quiz> quizzes = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param app App instance.
     * @param show True to show the frame once it has been initialized.
     */
    public QuizManagerForm(App app, boolean show) {
        // Construct the form
        super(app.getMainForm(), FORM_TITLE, true);

        // Store the app instance
        this.app = app;

        // Create the form UI
        createUIComponents();

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
        this.setSize(325, 400);

        // Make the frame non-resizable
        this.setResizable(true);

        // Create the main panel, to put the question and answers in
        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new GridBagLayout());

        // Configure the placement of the questions label, and add it to the questions panel
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 10, 0);
        pnlMain.add(mainLabel, c);

        // Create the quiz manager list and add it to the main panel
        JScrollPane quizList = createQuizList();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 2;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.insets = new Insets(0, 0, 0, 0);
        pnlMain.add(quizList, c);

        // Create the manage button panel
        JPanel manageButtonPanel = createManageButtonPanel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 1;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.insets = new Insets(0, 10, 0, 0);
        c.anchor = GridBagConstraints.NORTH;
        pnlMain.add(manageButtonPanel, c);

        // Create the control button panel
        JPanel controlButtonPanel = createControlButtonPanel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.insets = new Insets(10, 10, 0, 0);
        c.anchor = GridBagConstraints.SOUTH;
        pnlMain.add(controlButtonPanel, c);

        // Configure the main panel placement and add it to the frame
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.insets = new Insets(10, 10, 10, 10);
        this.add(pnlMain, c);

        // Set the window location to the system's default
        this.setLocationByPlatform(true);
        this.setLocationRelativeTo(null);

        // Update the button panel
        updateButtons();
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

        // Create a scroll pane with the quiz list and return it
        return new JScrollPane(this.quizList);
    }

    /**
     * Refresh the list of quizzes.
     */
    public void refreshList() {
        // Clear all current items
        this.quizListModel.clear();

        // Add the items
        this.quizzes.forEach(this.quizListModel::addElement);
    }

    /**
     * Create the button panel to manage the quizzes.
     *
     * @return Button panel.
     */
    public JPanel createManageButtonPanel() {
        // Create a panel to put the buttons in and set it's layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 10));

        // Create the buttons to add to the panel
        this.createButton = new JButton("Create");
        this.editButton = new JButton("Edit");
        this.moveUpButton = new JButton("Move up");
        this.moveDownButton = new JButton("Move down");
        this.deleteButton = new JButton("Delete");

        // Add the buttons to the panel
        buttonPanel.add(createButton);
        buttonPanel.add(editButton);
        buttonPanel.add(moveUpButton);
        buttonPanel.add(moveDownButton);
        buttonPanel.add(deleteButton);
        createButton.addActionListener(e -> createQuiz());
        editButton.addActionListener(e -> editQuiz());
        deleteButton.addActionListener(e -> deleteQuizzes());

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
        buttonPanel.setLayout(new GridLayout(1, 1, 10, 10));

        // Create the buttons to add to the panel
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> this.setVisible(false));

        // Add the buttons to the panel
        buttonPanel.add(closeButton);

        // Return the button panel
        return buttonPanel;
    }

    /**
     * Update the state of buttons in the button panel.
     */
    public void updateButtons() {
        // Get the number of selected items
        int selected = getSelectedCount();

        // Enable the edit button if one item is selected
        editButton.setEnabled(selected == 1);

        // Enable the move up, move down, and delete button if one or more items are selected
        moveUpButton.setEnabled(selected > 0);
        moveDownButton.setEnabled(selected > 0);
        deleteButton.setEnabled(selected > 0);
    }

    /**
     * Create a new quiz, ask for the name.
     */
    public void createQuiz() {
        // Ask for the quiz name
        String quizName = JOptionPane.showInputDialog(this, "Enter a name for the quiz:", "Create quiz", JOptionPane.INFORMATION_MESSAGE);

        // Make sure a name was entered
        if(quizName == null)
            return;

        // Trim the name
        quizName = quizName.trim();

        // Make sure the name is valid
        if(quizName.length() <= 0) {
            JOptionPane.showMessageDialog(this, "The name you've entered is invalid!", "Invalid name", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create the quiz
        Quiz quiz = new Quiz();
        quiz.setName(quizName);

        // Add the quiz to the list
        this.quizzes.add(quiz);

        // Refresh the list of quizzes
        refreshList();
    }

    /**
     * Edit the selected quiz.
     */
    public void editQuiz() {
        // Make sure just one item is selected
        if(getSelectedCount() != 1)
            return;

        // Get the selected quiz
        Quiz selected = (Quiz) this.quizList.getSelectedValue();

        // TODO: Edit quiz here!
        JOptionPane.showMessageDialog(this, "Edit quiz: " + selected.getName());
    }

    /**
     * Delete the selected quizzes.
     */
    public void deleteQuizzes() {
        // Make sure at least one item is selected
        if(getSelectedCount() <= 0)
            return;

        // Ask whether the user wants to delete the quizzes
        switch(JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the selected quizzes? This action can't be reverted.", "Delete quizzes", JOptionPane.YES_NO_OPTION)) {
        case JOptionPane.NO_OPTION:
        case JOptionPane.CANCEL_OPTION:
        case JOptionPane.CLOSED_OPTION:
            return;
        }

        // Delete the selected quizzes
        for(Object quiz : this.quizList.getSelectedValuesList())
            //noinspection RedundantCast
            this.quizzes.remove((Quiz) quiz);

        // Refresh the list
        refreshList();
    }

    /**
     * Get the number of selected items.
     *
     * @return Number of selected items.
     */
    public int getSelectedCount() {
        return this.quizList.getSelectedValuesList().size();
    }
}
