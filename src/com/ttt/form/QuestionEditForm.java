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

public class QuestionEditForm extends JDialog {

    /** Frame title. */
    private static final String FORM_TITLE = App.APP_NAME + " - Edit question";
    /** Number of answers to show. */
    private static final int ANSWER_COUNT = 4;

    /** App instance. */
    private App app;

    /**
     * Current question instance.
     */
    private Question question;

    /** The question label. */
    private JTextField questionField = new JTextField("<question>");
    /** The answer buttons. */
    private JRadioButton[] answerRadioButtons = new JRadioButton[ANSWER_COUNT];
    /** The answer labels. */
    private JTextField[] answerFields = new JTextField[ANSWER_COUNT];

    /**
     * Constructor.
     *
     * @param parent The parent window.
     * @param app App instance.
     * @param question The question.
     * @param show True to show the frame once it has been initialized.
     */
    public QuestionEditForm(Window parent, App app, Question question, boolean show) {
        // Construct the form
        super(parent, FORM_TITLE);

        // Store the app instance
        this.app = app;
        this.question = question;

        // Create the form UI
        createUIComponents();

        // Set the question
        refreshQuestion(this.question);

        // Close application when closing form
        // TODO: Should we keep this?
        //this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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

        // Set the window location to the system's default
        this.setLocationByPlatform(true);
        this.setLocationRelativeTo(null);

        // Create the main panel, to put the question and answers in
        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new GridBagLayout());

        // Create the questions panel and set the layout
        JPanel pnlQuestion = new JPanel();
        pnlQuestion.setLayout(new GridBagLayout());

        // Configure the placement of the questions label, and add it to the questions panel
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 16, 0);
        c.weightx = 1.0;
        // TODO: Set the textfield size
        /*questionField.setFixedWidth(350);
        questionField.setFixedHeight(55);
        questionField.setResizeWidthIfLarger(true);
        questionField.setResizeHeightIfLarger(true);
        questionField.setVerticalAlignment(SwingCons8tants.CENTER);*/
        pnlQuestion.add(questionField, c);

        // Configure the placement of the questions panel and add it to the main panel
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 0, 0);
        pnlMain.add(pnlQuestion, c);

        // Create the answers panel, to put the answers in
        JPanel answersPnl = new JPanel();
        answersPnl.setLayout(new GridBagLayout());

        // Create and add answer buttons to the answers panel
        for(int i = 0; i < ANSWER_COUNT; i++) {
            // Specify the question index as final
            final int iFinal = i;

            // Create the buttons
            answerRadioButtons[i] = new JRadioButton();

            // Configure the button placement
            c.gridx = 0;
            c.gridy = i;
            c.insets = new Insets(3, 0, 3, 0);
            c.weightx = 0.0;

            // Add the button
            answersPnl.add(answerRadioButtons[i], c);

            // Create an action listener for the button
            answerRadioButtons[i].addActionListener(e -> app.selectedAnswer(iFinal));
        }

        // Create and add answer labels to the answers panel
        for(int i = 0; i < ANSWER_COUNT; i++) {
            // Create the label
            answerFields[i] = new JTextField("<answer " + i + ">");

            // Configure the label placement
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 1;
            c.gridy = i;
            c.insets = new Insets(0, 10, 0, 5);
            c.weightx = 1.0;

            // Add the answer label to the panel
            answersPnl.add(answerFields[i], c);
        }

        // Configure the answers panel placement and add it to the main frame
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0, 0, 0, 0);
        pnlMain.add(answersPnl, c);

        // Configure the main panel placement and add it to the frame
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10);
        this.add(pnlMain, c);
    }

    /**
     * Get the current question.
     *
     * @return Question.
     */
    public Question getQuestion() {
        return this.question;
    }

    /**
     * Set the question that is shown.
     *
     * @param question Question.
     */
    public void refreshQuestion(Question question) {
        // Set the question label
        questionField.setText(question.getQuestion(false));

        // Set the labels
        for(int i = 0; i < ANSWER_COUNT; i++) {
            // Set whether the answer is correct
            answerRadioButtons[i].setSelected(question.isCorrectAnswerIndex(i));

            // Put the answers in the answer fields
            answerFields[i].setText(question.getAnswer(i, false));
        }

        // Pack the frame
        pack();

        // Force the whole frame to repaint, to prevent graphical artifacts on some operating systems
        this.repaint();
    }
}