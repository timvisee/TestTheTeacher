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
import com.ttt.util.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class QuestionEditForm extends JDialog {

    /** Frame title. */
    private static final String FORM_TITLE = App.APP_NAME + " - Vraag bewerken";
    /** Number of answers to show. */
    private static final int ANSWER_COUNT = 4;

    /** App instance. */
    private App app;

    /**
     * Current question instance.
     */
    private Question question;

    /** The question label. */
    private JTextArea questionField = new JTextArea("Vraag?");
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
        super(parent, FORM_TITLE, ModalityType.DOCUMENT_MODAL);

        // Store the app instance
        this.app = app;
        this.question = question;

        // Create the form UI
        createUIComponents();

        // Set the question
        refreshQuestion(this.question);

        // Do not close the window when pressing the red cross, execute the close method instead
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) { }

            @Override
            public void windowClosing(WindowEvent e) {
                closeFrame();
            }

            @Override
            public void windowClosed(WindowEvent e) { }

            @Override
            public void windowIconified(WindowEvent e) { }

            @Override
            public void windowDeiconified(WindowEvent e) { }

            @Override
            public void windowActivated(WindowEvent e) { }

            @Override
            public void windowDeactivated(WindowEvent e) { }
        });

        // Set the frame sizes
        this.setMinimumSize(new Dimension(350, 290));
        this.setPreferredSize(new Dimension(600, 350));
        this.setSize(new Dimension(600, 350));

        // Set the window location to the system's default
        this.setLocationByPlatform(true);
        this.setLocationRelativeTo(parent);

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

        // Create the questions panel and set the layout
        JPanel pnlQuestion = new JPanel();
        pnlQuestion.setLayout(new GridBagLayout());

        // Configure the placement of the questions label, and add it to the questions panel
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 16, 10);
        /*c.weightx = 0.0;
        c.weighty = 0.0;
        c.anchor = GridBagConstraints.WEST;*/
        pnlMain.add(new JLabel("Vul de vraag en de antwoorden in de onderstaande velden in."), c);

        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 16, 10);
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.anchor = GridBagConstraints.WEST;
        pnlMain.add(new JLabel("Vraag:"), c);

        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0, 0, 0, 10);
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.anchor = GridBagConstraints.WEST;
        pnlMain.add(new JLabel("Antwoorden:"), c);

        // Configure the placement of the questions label, and add it to the questions panel
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 0, 0);
        c.weightx = 1.0;
        c.weighty = 1.0;
        pnlQuestion.add(new JScrollPane(this.questionField), c);

        // Configure the placement of the questions panel and add it to the main panel
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.insets = new Insets(0, 0, 16, 0);
        pnlMain.add(pnlQuestion, c);

        // Create the answers panel, to put the answers in
        JPanel answersPnl = new JPanel();
        answersPnl.setLayout(new GridBagLayout());

        // Create a radio button group
        ButtonGroup group = new ButtonGroup();

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
            c.weighty = 0.0;

            // Add the button
            answersPnl.add(answerRadioButtons[i], c);

            // Create an action listener for the button
            answerRadioButtons[i].addActionListener(e -> app.selectedAnswer(iFinal));

            // Set the button group
            group.add(answerRadioButtons[i]);
        }

        // Create and add answer labels to the answers panel
        for(int i = 0; i < ANSWER_COUNT; i++) {
            // Create the label
            answerFields[i] = new JTextField("Antwoord " + i + "");

            // Configure the label placement
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 1;
            c.gridy = i;
            c.insets = new Insets(0, 10, 0, 0);
            c.weightx = 1.0;
            c.weighty = 0.0;

            // Add the answer label to the panel
            answersPnl.add(answerFields[i], c);
        }

        // Configure the answers panel placement and add it to the main panel
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 1.0;
        c.weighty = 0.0;
        c.insets = new Insets(0, 0, 0, 0);
        pnlMain.add(answersPnl, c);

        // Create the control button panel and add it to the main panel
        JPanel controlsPanel = createControlButtonPanel();
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.EAST;
        pnlMain.add(controlsPanel, c);

        // Configure the main panel placement and add it to the frame
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.insets = new Insets(10, 10, 10, 10);
        this.add(pnlMain, c);
    }

    /**
     * Create the button panel to control the form.
     *
     * @return Button panel.
     */
    public JPanel createControlButtonPanel() {
        // Create a panel to put the buttons in and set it's layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 10));

        // Create the buttons to add to the panel
        JButton okButton = new JButton("Ok");
        JButton applyButton = new JButton("Toepassen");
        JButton cancelButton = new JButton("Annuleren");
        okButton.addActionListener(e -> {
            // Save the questions
            if(!applyQuestion())
                return;

            // Close the frame
            dispose();
        });
        applyButton.addActionListener(e -> applyQuestion());
        cancelButton.addActionListener(e -> closeFrame());

        // Add the buttons to the panel
        buttonPanel.add(okButton);
        buttonPanel.add(applyButton);
        buttonPanel.add(cancelButton);

        // Return the button panel
        return buttonPanel;
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
        questionField.setText(StringUtils.decodeHtml(question.getQuestion(false)));

        // Set the labels
        for(int i = 0; i < ANSWER_COUNT; i++) {
            // Set whether the answer is correct
            answerRadioButtons[i].setSelected(question.isCorrectAnswerIndex(i));

            // Put the answers in the answer fields
            answerFields[i].setText(StringUtils.decodeHtml(question.getAnswer(i, false)));
        }

        // Pack the frame
        //pack();

        // Force the whole frame to repaint, to prevent graphical artifacts on some operating systems
        this.repaint();
    }

    /**
     * Get the correct answer index.
     *
     * @return Get the correct answer index.
     */
    public int getCorrectAnswer() {
        // Loop through all the radio buttons to see what the correct answer is
        for(int i = 0; i < this.answerRadioButtons.length; i++)
            if(this.answerRadioButtons[i].isSelected())
                return i;

        // No one selected, return the first one
        return 0;
    }

    /**
     * Apply and save the questions.
     *
     * @return True if save succeed, false otherwise.
     */
    public boolean applyQuestion() {
        // Make sure the question is valid
        if(this.questionField.getText().trim().length() <= 0) {
            JOptionPane.showMessageDialog(this, "De vraag is ongeldig en kon niet opgeslagen worden.", "Ongeldige vraag", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Set the question
        this.question.setQuestion(StringUtils.encodeHtml(this.questionField.getText()));

        // Set the correct answer
        this.question.setCorrectAnswerIndex(getCorrectAnswer());

        // Save the answers
        for(int i = 0; i < ANSWER_COUNT; i++)
            this.question.setAnswer(i, StringUtils.encodeHtml(this.answerFields[i].getText()));

        // TODO: Save the questions to a file?

        // Save succeed, return the result
        return true;
    }

    /**
     * Close the frame. Ask whether the user wants to save the changes.
     */
    public void closeFrame() {
        // Only ask to save if there are any unsaved changes
        if(hasUnsavedChanges()) {
            // Ask whether the user wants to save the questions
            switch(JOptionPane.showConfirmDialog(this, "Wilt ik de wijzigingen opslaan?", "Vraag gewijzigd", JOptionPane.YES_NO_CANCEL_OPTION)) {
            case JOptionPane.YES_OPTION:
                // Save the changes
                if(!applyQuestion())
                    break;

            case JOptionPane.NO_OPTION:
                // Dispose the frame
                this.dispose();
                break;
            }

        } else
            this.dispose();
    }

    /**
     * Check whether this question has unsaved changes.
     *
     * @return True if this question has unsaved changes, false if not.
     */
    public boolean hasUnsavedChanges() {
        // Check whether the question has changed
        if(!this.question.getQuestion(false).equals(StringUtils.encodeHtml(this.questionField.getText())))
            return true;

        // Check whether the correct answer is different
        if(!this.question.isCorrectAnswerIndex(getCorrectAnswer()))
            return true;

        // Loop through all the answers to check whether they are changed
        for(int i = 0; i < ANSWER_COUNT; i++)
            if(!this.question.getAnswer(i, false).equals(StringUtils.encodeHtml(this.answerFields[i].getText())))
                return true;

        // No unsaved changes detected, return false
        return false;
    }
}
