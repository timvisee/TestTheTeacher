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
import com.ttt.form.component.TTTLabel;
import com.ttt.quiz.Quiz;

import javax.swing.*;
import java.awt.*;

public class QuestionForm extends JFrame {

    /** Frame title. */
    private static final String FORM_TITLE = App.APP_NAME;
    /** Number of answers to show. */
    private static final int ANSWER_COUNT = 4;

    /** App instance. */
    private App app;

    /**
     * Current quiz instance.
     */
    private Quiz currentQuiz;
    /**
     * Current question instance.
     */
    private Question currentQuestion;

    /** The question label. */
    private TTTLabel questionLabel = new TTTLabel("<question>");
    /** The answer buttons. */
    private JButton[] answerButtons = new JButton[ANSWER_COUNT];
    /** The answer labels. */
    private TTTLabel[] answerLabels = new TTTLabel[ANSWER_COUNT];

    /**
     * Constructor.
     *
     * @param app App instance.
     * @param show True to show the frame once it has been initialized.
     */
    public QuestionForm(App app, boolean show) {
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
        questionLabel.setFixedWidth(350);
        questionLabel.setFixedHeight(55);
        questionLabel.setResizeWidthIfLarger(true);
        questionLabel.setResizeHeightIfLarger(true);
        questionLabel.setVerticalAlignment(SwingConstants.CENTER);
        pnlQuestion.add(questionLabel, c);

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
            answerButtons[i] = new JButton(String.valueOf((char) ('A' + i))); // A, B, C, D ...

            // Configure the button placement
            c.gridx = 0;
            c.gridy = i;
            c.insets = new Insets(3, 0, 3, 0);
            c.weightx = 0.0;

            // Add the button
            answersPnl.add(answerButtons[i], c);

            // Create an action listener for the button
            answerButtons[i].addActionListener(e -> app.selectedAnswer(iFinal));
        }

        // Create and add answer labels to the answers panel
        for(int i = 0; i < ANSWER_COUNT; i++) {
            // Create the label
            answerLabels[i] = new TTTLabel("<answer " + i + ">");

            // Configure the label placement
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 1;
            c.gridy = i;
            c.insets = new Insets(0, 10, 0, 5);
            c.weightx = 1.0;

            // Add the answer label to the panel
            answersPnl.add(answerLabels[i], c);
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
        return this.currentQuestion;
    }

    /**
     * Set the question that is shown.
     *
     * @param question Question.
     */
    public void setQuestion(Question question) {
        // Set the current question
        this.currentQuestion = question;

        // Set the question label
        questionLabel.setText(question.getQuestion(true));

        // Set the labels
        for(int i = 0; i < ANSWER_COUNT; i++)
            answerLabels[i] .setText(question.getAnswer(i, true));

        // Pack the frame
        pack();

        // Force the whole frame to repaint, to prevent graphical artifacts on some operating systems
        this.repaint();
    }

    /**
     * Get the current quiz instance.
     *
     * @return Quiz.
     */
    public Quiz getQuiz() {
        return this.currentQuiz;
    }

    /**
     * Set the current quiz instance.
     *
     * @param quiz Quiz.
     */
    public void setQuiz(Quiz quiz) {
        // Set the current quiz
        this.currentQuiz = quiz;

        // Set the frame title
        this.setTitle(FORM_TITLE + " - " + quiz.getName());
    }
}
