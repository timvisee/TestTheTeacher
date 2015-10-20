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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizManagerForm extends JDialog {

    /** Frame title. */
    private static final String FORM_TITLE = App.APP_NAME + " - Quiz manager";

    /** App instance. */
    private App app;

    /** The quiz manager label. */
    private JLabel mainLabel = new JLabel("<html>Maak, wijzig of verwijder een eigen quiz. Gebruik de knoppen aan de rechterzijde. ");

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

        // Load the quizzes
        // TODO: Do we need to copy the list?
        this.quizzes = this.app.getQuizManager().getQuizzes();

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
        this.setMinimumSize(new Dimension(275, 365));
        this.setPreferredSize(new Dimension(325, 400));
        this.setSize(new Dimension(325, 400));

        // Set the window location to the system's default
        this.setLocationByPlatform(true);
        this.setLocationRelativeTo(app.getMainForm());

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
        this.quizList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                 editQuiz();
                }
            }

        });

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
        createButton.addActionListener(e -> createQuiz());
        editButton.addActionListener(e -> editQuiz());
        moveUpButton.addActionListener(e -> moveQuizzesUp());
        moveDownButton.addActionListener(e -> moveQuizzesDown());
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
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));

        // Create the buttons to add to the panel
        JButton okButton = new JButton("Ok");
        JButton applyButton = new JButton("Toepassen");
        JButton cancelButton = new JButton("Annuleren");
        okButton.addActionListener(e -> {
            // Save the quizzes
            applyQuizzes();

            // Close the frame
            dispose();
        });
        applyButton.addActionListener(e -> applyQuizzes());
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
     * Create a new quiz, ask for the name.
     */
    public void createQuiz() {
        // Ask for the quiz name
        String quizName = JOptionPane.showInputDialog(this, "Voer een naam in voor uw quiz:", "Voeg toe", JOptionPane.INFORMATION_MESSAGE);

        // Make sure a name was entered
        if(quizName == null)
            return;

        // Trim the name
        quizName = quizName.trim();

        // Make sure the name is valid
        if(quizName.length() <= 0) {
            JOptionPane.showMessageDialog(this, "Ongeldige naam.", "Ongeldig", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create the quiz
        Quiz quiz = new Quiz();
        quiz.setName(quizName);

        // Show the quiz edit panel
        new QuizForm(this, this.app, quiz, true);

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

        // Show the quiz edit panel
        new QuizForm(this, this.app, selected, true);
    }

    /**
     * Move the selected quizzes up.
     */
    public void moveQuizzesUp() {
        // Make sure at least one quiz is selected
        if(getSelectedCount() <= 0)
            return;

        // Get the indices
        int[] indices = this.quizList.getSelectedIndices();

        // Move the quizzes
        if(moveQuizzes(this.quizList.getSelectedIndices(), -1))
            for(int i = 0; i < indices.length; i++)
                indices[i]--;

        // Set the selected indices
        this.quizList.setSelectedIndices(indices);

        // Update the list
        refreshList();
    }

    /**
     * Move the selected quizzes down.
     */
    public void moveQuizzesDown() {
        // Make sure at least one quiz is selected
        if(getSelectedCount() <= 0)
            return;

        // Get the indices
        int[] indices = this.quizList.getSelectedIndices();

        // Move the quizzes
        if(moveQuizzes(this.quizList.getSelectedIndices(), 1))
            for(int i = 0; i < indices.length; i++)
                indices[i]++;

        // Set the selected indices
        this.quizList.setSelectedIndices(indices);

        // Update the list
        refreshList();
    }

    /**
     * Move the quizzes.
     *
     * @param quizIndexes Indexes of quizzes to move.
     * @param move How much to move.
     *
     * @return True if any item was moved, false if not.
     */
    private boolean moveQuizzes(int[] quizIndexes, int move) {
        // Get the lowest and highest new index
        int lowest = quizIndexes[0] + move;
        int highest = quizIndexes[0] + move;

        // Loop through the quiz indexes and update the lowest and highest values
        for(int i = 1; i < quizIndexes.length; i++) {
            lowest = Math.min(quizIndexes[i] + move, lowest);
            highest = Math.max(quizIndexes[i] + move, highest);
        }

        // Make sure the quizzes can be moved to that position
        if(lowest < 0 || highest >= this.quizzes.size())
            return false;

        // Sort the array with indexes
        Arrays.sort(quizIndexes);

        // Inverse the list if they should be moved upwards
        if(move > 0) {
            for(int i = 0; i < quizIndexes.length / 2; i++) {
                int temp = quizIndexes[i];
                quizIndexes[i] = quizIndexes[quizIndexes.length - i - 1];
                quizIndexes[quizIndexes.length - i - 1] = temp;
            }
        }

        // Move all the quizzes
        for(int i : quizIndexes)
            Collections.swap(this.quizzes, i, i + move);

        // Return the result
        return true;
    }

    /**
     * Delete the selected quizzes.
     */
    public void deleteQuizzes() {
        // Make sure at least one item is selected
        if(getSelectedCount() <= 0)
            return;

        // Ask whether the user wants to delete the quizzes
        switch(JOptionPane.showConfirmDialog(this, "Weet u zeker dat u het wilt verwijderen? De actie kan niet ongedaan worden gemaakt.", "Verwijderen", JOptionPane.YES_NO_OPTION)) {
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

    /**
     * Apply and save the quizzes.
     */
    public void applyQuizzes() {
        // Store the quizzes
        this.app.getQuizManager().setQuizzes(this.quizzes);

        // TODO: Save the quizzes to a file?
    }

    /**
     * Close the frame. Ask whether the user wants to save the changes.
     */
    public void closeFrame() {
        // Ask whether the user wants to save the quizzes
        switch(JOptionPane.showConfirmDialog(this, "Wilt u uw quiz opslaan?", "Quiz manager", JOptionPane.YES_NO_CANCEL_OPTION)) {
        case JOptionPane.YES_OPTION:
            // Save the changes
            applyQuizzes();

        case JOptionPane.NO_OPTION:
            // Dispose the frame
            this.dispose();
            break;
        }
    }
}
