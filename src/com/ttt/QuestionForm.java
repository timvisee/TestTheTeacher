package com.ttt;

import javax.swing.*;
import java.awt.*;

public class QuestionForm extends JFrame {

    /** Frame title. */
    private static final String FORM_TITLE = App.APP_NAME;
    /** Number of answers to show. */
    private static final int ANSWER_COUNT = 4;

    /** App instance. */
    private App app;

    /** The question label. */
    private JLabel lblQuestion = new JLabel("<Question>");
    /** The answer buttons. */
    private JButton[] answerBtns = new JButton[ANSWER_COUNT];
    /** The answer labels. */
    private JLabel[] answerLbls = new JLabel[ANSWER_COUNT];

    /**
     * Constructor.
     */
    public QuestionForm() {
        // Construct the form
        super(FORM_TITLE);

        // Create the form UI
        createUIComponents();

        // Close application when closing form
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Show the form
        this.setVisible(true);
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
        this.setSize(350, 215);

        // Make the frame non-resizable
        this.setResizable(false);

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
        c.insets = new Insets(3, 5, 15, 5);
        c.weightx = 1.0;
        lblQuestion.setText("Question");
        pnlQuestion.add(lblQuestion, c);

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
            // Create the buttons
            answerBtns[i] = new JButton(String.valueOf((char) ('A' + i))); // A, B, C, D ...

            // Configure the button placement
            c.gridx = 0;
            c.gridy = i;
            c.insets = new Insets(3, 5, 3, 5);
            c.weightx = 0.0;

            // Add the button
            answersPnl.add(answerBtns[i], c);
        }

        // Create and add answer labels to the answers panel
        for(int i = 0; i < ANSWER_COUNT; i++) {
            // Create the label
            answerLbls[i] = new JLabel("Answer " + i);

            // Configure the label placement
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 1;
            c.gridy = i;
            c.insets = new Insets(0, 5, 0, 5);
            c.weightx = 1.0;

            // Add the answer label to the panel
            answersPnl.add(answerLbls[i], c);
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
     * Set the question that is shown.
     *
     * @param question Question.
     */
    public void setQuestion(Question question) {
        // Set the question label
        lblQuestion.setText(question.getQuestion());

        // Set the labels
        for(int i = 0; i < ANSWER_COUNT; i++)
            answerLbls[i] = new JLabel(question.getAnswer(i));
    }
}
