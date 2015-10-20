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
import com.ttt.question.Question;

import javax.swing.*;
import java.awt.*;

public class QuizManagerForm extends JFrame {

    /** Frame title. */
    private static final String FORM_TITLE = App.APP_NAME + " - Quiz Manager";

    /** App instance. */
    private App app;

    /** The quiz manager label. */
    private JLabel mainLabel = new JLabel("<html>Quiz manager.");

    /**
     * Constructor.
     *
     * @param app App instance.
     * @param show True to show the frame once it has been initialized.
     */
    public QuizManagerForm(App app, boolean show) {
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
        this.setSize(400, 215);

        // Make the frame non-resizable
        this.setResizable(true);

        // Create the main panel, to put the question and answers in
        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new GridBagLayout());

        // Configure the placement of the questions label, and add it to the questions panel
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 10, 0);
        pnlMain.add(mainLabel, c);

        // Create the quiz manager list and add it to the main panel
        JComponent quizList = createQuizList();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0, 5, 0, 5);
        c.weightx = 1.0;
        c.weighty = 1.0;
        quizList.setBackground(Color.red);
        pnlMain.add(quizList, c);

        // Configure the main panel placement and add it to the frame
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10);
        this.add(pnlMain, c);

        // Set the window location to the system's default
        this.setLocationByPlatform(true);
        this.setLocationRelativeTo(null);
    }

    // TODO: Handle quizzes, instead of questions here!
    /**
     * Create a list to manage the quizzes in.
     *
     * @return Panel with the list.
     */
    public JComponent createQuizList() {

        // Create a list model for the quizzes
        // TODO: Dynamically add all quizzes!
        DefaultListModel<Question> questionModel = new DefaultListModel<>();

        // Add all quizzes
        for(int i = 0; i < this.app.getQuestionCount(); i++)
            questionModel.addElement(this.app.getQuestion(i));

        // Create the list
        JList<Question> quizList = new JList<>(questionModel);

        // Create a panel for the list and set the layout
        JScrollPane scrolLPane = new JScrollPane(quizList);

        // Return the panel
        return scrolLPane;
    }
}
