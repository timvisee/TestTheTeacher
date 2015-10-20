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
import com.ttt.quiz.Quiz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizForm extends JDialog {

    /** Frame title. */
    private static final String FORM_TITLE = App.APP_NAME + " - Quiz bewerken";

    /** App instance. */
    private App app;

    /** The quiz manager label. */
    private JLabel mainLabel = new JLabel("<html>Maak, wijzig of verwijder een vraag. Gebruik de knoppen aan de rechterzijde.");

    /**
     * Quiz name field instance.
     */
    private JTextField quizNameField = new JTextField("Quiz name");

    /**
     * Question list model instance.
     */
    private DefaultListModel<Question> questionListModel;

    /**
     * Question list instance.
     */
    private JList questionList;

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
     * The quiz instance.
     */
    private Quiz quiz;

    /**
     * List of the questions being shown.
     */
    private List<Question> questions = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param parent The quiz manager form as parent.
     * @param app App instance.
     * @param quiz Quiz instance.
     * @param show True to show the frame once it has been initialized.
     */
    public QuizForm(QuizManagerForm parent, App app, Quiz quiz, boolean show) {
        // Construct the form
        super(parent, FORM_TITLE, true);

        // Store the app instance
        this.app = app;

        // Set the quiz instance
        this.quiz = quiz;

        // Load the questions
        // TODO: Do we need to copy the list?
        this.questions = this.quiz.getQuestions();

        // Create the form UI
        createUIComponents();

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
        this.setMinimumSize(new Dimension(325, 400));
        this.setPreferredSize(new Dimension(600, 450));
        this.setSize(new Dimension(600, 450));

        // Set the window location to the system's default
        this.setLocationByPlatform(true);
        this.setLocationRelativeTo(parent);

        // Create a new question if there isn't any question yet
        if(this.questions.size() <= 0)
            createQuestion();

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

        // Make the frame non-resizable
        this.setResizable(true);

        // Create the main panel, to put the question and answers in
        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new GridBagLayout());

        // Configure the placement of the questions label, and add it to the questions panel
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.insets = new Insets(0, 0, 25, 0);
        pnlMain.add(mainLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 10, 10);
        pnlMain.add(new JLabel("Naam:"), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 2;
        c.insets = new Insets(0, 0, 10, 10);
        pnlMain.add(new JLabel("Vragen:"), c);

        this.quizNameField.setText(this.quiz.getName());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(0, 0, 10, 0);
        pnlMain.add(this.quizNameField, c);

        // Create the quiz manager list and add it to the main panel
        JScrollPane quizList = createQuestionList();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 2;
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
        c.gridy = 2;
        c.gridheight = 1;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.insets = new Insets(0, 10, 0, 0);
        c.anchor = GridBagConstraints.NORTH;
        pnlMain.add(manageButtonPanel, c);

        // Create the control button panel
        JPanel controlButtonPanel = createControlButtonPanel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 3;
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

        // Update the button panel
        updateButtons();
    }

    /**
     * Create a list to manage the questions in.
     *
     * @return Scroll pane with list.
     */
    public JScrollPane createQuestionList() {
        // Create the default list model
        this.questionListModel = new DefaultListModel<>();

        // Refresh the list of questions to add them to the list model
        refreshList();

        // Create the list and create an empty border
        this.questionList = new JList<>(this.questionListModel);
        this.questionList.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Update the button panel on selection change
        this.questionList.addListSelectionListener(e -> updateButtons());

        // Edit a question if it's double clicked
        this.questionList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if(evt.getClickCount() == 2) {
                    editQuestion();
                }
            }
        });

        // Create a scroll pane with the quiz list and return it
        return new JScrollPane(this.questionList);
    }

    /**
     * Refresh the list of questions.
     */
    public void refreshList() {
        // Clear all current items
        this.questionListModel.clear();

        // Add the items
        this.questions.forEach(this.questionListModel::addElement);
    }

    /**
     * Create the button panel to manage the questions.
     *
     * @return Button panel.
     */
    public JPanel createManageButtonPanel() {
        // Create a panel to put the buttons in and set it's layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 10));

        // Create the buttons to add to the panel
        this.createButton = new JButton("Toevoegen");
        this.editButton = new JButton("Wijzigen");
        this.moveUpButton = new JButton("Omhoog");
        this.moveDownButton = new JButton("Omlaag");
        this.deleteButton = new JButton("Verwijder");

        // Add the buttons to the panel
        buttonPanel.add(createButton);
        buttonPanel.add(editButton);
        buttonPanel.add(moveUpButton);
        buttonPanel.add(moveDownButton);
        buttonPanel.add(deleteButton);
        createButton.addActionListener(e -> createQuestion());
        editButton.addActionListener(e -> editQuestion());
        moveUpButton.addActionListener(e -> moveQuestionsUp());
        moveDownButton.addActionListener(e -> moveQuestionsDown());
        deleteButton.addActionListener(e -> deleteQuestions());

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
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));

        // Create the buttons to add to the panel
        JButton okButton = new JButton("Ok");
        JButton applyButton = new JButton("Toepassen");
        JButton cancelButton = new JButton("Annuleren");
        okButton.addActionListener(e -> {
            // Save the questions
            applyQuestions();

            // Close the frame
            dispose();
        });
        applyButton.addActionListener(e -> applyQuestions());
        cancelButton.addActionListener(e -> closeFrame());

        // Add the buttons to the panel
        buttonPanel.add(okButton);
        buttonPanel.add(applyButton);
        buttonPanel.add(cancelButton);

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
     * Create a new question.
     */
    public void createQuestion() {
        // Create an empty question
        Question question = new Question();

        // Edit the question
        new QuestionEditForm(this, this.app, question, true);

        // Make sure the question is valid
        if(question.getQuestion(false).trim().length() <= 0) {
            JOptionPane.showMessageDialog(this, "De vraag is ongeldig, en kon niet worden toegevoegd.", "Ongeldige vraag", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Add the question
        this.questions.add(question);

        // Refresh the list of questions
        refreshList();
    }

    /**
     * Edit the selected question.
     */
    public void editQuestion() {
        // Make sure just one item is selected
        if(getSelectedCount() != 1)
            return;

        // Get the selected question
        Question selected = (Question) this.questionList.getSelectedValue();

        // Edit the selected question
        new QuestionEditForm(this, this.app, selected, true);

        // Refresh the list
        refreshList();
    }

    /**
     * Move the selected questions up.
     */
    public void moveQuestionsUp() {
        // Make sure at least one quiz is selected
        if(getSelectedCount() <= 0)
            return;

        // Get the indices
        int[] indices = this.questionList.getSelectedIndices();

        // Move the questions
        if(moveQuestions(this.questionList.getSelectedIndices(), -1))
            for(int i = 0; i < indices.length; i++)
                indices[i]--;

        // Set the selected indices
        this.questionList.setSelectedIndices(indices);

        // Update the list
        refreshList();
    }

    /**
     * Move the selected questions down.
     */
    public void moveQuestionsDown() {
        // Make sure at least one quiz is selected
        if(getSelectedCount() <= 0)
            return;

        // Get the indices
        int[] indices = this.questionList.getSelectedIndices();

        // Move the questions
        if(moveQuestions(this.questionList.getSelectedIndices(), 1))
            for(int i = 0; i < indices.length; i++)
                indices[i]++;

        // Set the selected indices
        this.questionList.setSelectedIndices(indices);

        // Update the list
        refreshList();
    }

    /**
     * Move the questions.
     *
     * @param questionIndexes Indexes of questions to move.
     * @param move How much to move.
     *
     * @return True if any item was moved, false if not.
     */
    private boolean moveQuestions(int[] questionIndexes, int move) {
        // Get the lowest and highest new index
        int lowest = questionIndexes[0] + move;
        int highest = questionIndexes[0] + move;

        // Loop through the quiz indexes and update the lowest and highest values
        for(int i = 1; i < questionIndexes.length; i++) {
            lowest = Math.min(questionIndexes[i] + move, lowest);
            highest = Math.max(questionIndexes[i] + move, highest);
        }

        // Make sure the questions can be moved to that position
        if(lowest < 0 || highest >= this.questions.size())
            return false;

        // Sort the array with indexes
        Arrays.sort(questionIndexes);

        // Inverse the list if they should be moved upwards
        if(move > 0) {
            for(int i = 0; i < questionIndexes.length / 2; i++) {
                int temp = questionIndexes[i];
                questionIndexes[i] = questionIndexes[questionIndexes.length - i - 1];
                questionIndexes[questionIndexes.length - i - 1] = temp;
            }
        }

        // Move all the questions
        for(int i : questionIndexes)
            Collections.swap(this.questions, i, i + move);

        // Return the result
        return true;
    }

    /**
     * Delete the selected questions.
     */
    public void deleteQuestions() {
        // Make sure at least one item is selected
        if(getSelectedCount() <= 0)
            return;

        // Ask whether the user wants to delete the questions
        switch(JOptionPane.showConfirmDialog(this, "Weet u zeker dat u de geselecteerde vragen wilt verwijderen? Deze actie kan niet ongedaan worden gemaakt.", "Verwijder vragen", JOptionPane.YES_NO_OPTION)) {
            case JOptionPane.NO_OPTION:
            case JOptionPane.CANCEL_OPTION:
            case JOptionPane.CLOSED_OPTION:
                return;
        }

        // Delete the selected questions
        for(Object question : this.questionList.getSelectedValuesList())
            //noinspection RedundantCast
            this.questions.remove((Question) question);

        // Refresh the list
        refreshList();
    }

    /**
     * Get the number of selected items.
     *
     * @return Number of selected items.
     */
    public int getSelectedCount() {
        return this.questionList.getSelectedValuesList().size();
    }

    /**
     * Apply and save the questions.
     */
    public void applyQuestions() {
        // Set the name
        this.quiz.setName(this.quizNameField.getText());

        // Set the questions
        this.quiz.setQuestions(this.questions);

        // TODO: Save the questions to a file?
    }

    /**
     * Close the frame. Ask whether the user wants to save the changes.
     */
    public void closeFrame() {
        // Only ask to save if there are unsaved changes
        if(hasUnsavedChanges()) {
            // Ask whether the user wants to save the questions
            switch(JOptionPane.showConfirmDialog(this, "Wilt u de vragen opslaan?", "Quiz", JOptionPane.YES_NO_CANCEL_OPTION)) {
                case JOptionPane.YES_OPTION:
                    // Save the changes
                    applyQuestions();

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
        // Compare the quiz name
        if(!this.quiz.getName().equals(this.quizNameField.getText()))
            return true;

        // Compare the number of questions
        if(this.quiz.getQuestionCount() != this.questions.size())
            return true;

        // Compare the questions
        for(int i = 0; i < this.questions.size(); i++)
            if(!this.quiz.getQuestion(i).equals(this.questions.get(i)))
                return true;

        // There don't seem to be any unsaved changes, return the result
        return false;
    }
}
