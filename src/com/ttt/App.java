/**
 * TestTheTeacher.
 * Test the teacher to see whether he or she is really good enough at basing programming.
 * A project for a school of applied sciences.
 *
 * @author MingYan Li, Tim Visee
 * @copyright Copyright (c) MingYan Li & Tim Visee 2015. All rights reserved.
 * @license GNU GPL v3.0
 */

package com.ttt;

import com.ttt.form.MainForm;
import com.ttt.form.QuestionForm;
import com.ttt.question.Question;
import com.ttt.util.StringUtils;
import com.ttt.util.WindowUtils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class App {

    /**
     * App name.
     */
    public static final String APP_NAME = "Test The Teacher";

    /**
     * List of all questions.
     */
    private List<Question> questions = new ArrayList<>();

    /**
     * The current question.
     */
    private int currentQuestion = 0;

    /**
     * The current score. Number of correct answers.
     */
    private int score = 0;

    /**
     * The main form instance.
     */
    private MainForm mainForm;

    /**
     * The question form instance.
     */
    private QuestionForm questionForm;

    /**
     * Constructor.
     *
     * @param init True to initialize the application, false otherwise.
     */
    public App(boolean init) {
        // Initialize
        if (init)
            init();
    }

    /**
     * Get the current score.
     *
     * @return Score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Set the current score.
     *
     * @param score Current score.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Increase the score by one.
     */
    public void addScore(){
        this.score++;
    }

    /**
     * Initialize the application.
     */
    public void init() {
        // Application initializing, show a message
        System.out.println("Initializing " + App.APP_NAME + "...");

        // Use the system's GUI look and feel, not the Java one
        WindowUtils.useNativeLookAndFeel();

        // Add all questions
        addQuestions();

        // Initialize the main form
        this.mainForm = new MainForm(this, false);

        // Create and open the question form
        this.questionForm = new QuestionForm(this, false);

        // Show the first question in the frame
        this.questionForm.setQuestion(getCurrentQuestion());

        // Application initialized, show a message
        System.out.println("Initialized " + App.APP_NAME + " successfully! Cave Johnson here!");
    }

    /**
     * Start the application, after it has been initialized.
     */
    public void start() {
        // Start the application
        System.out.println("Starting " + App.APP_NAME + "...");

        // hide questionForm
        this.questionForm.setVisible(false);

        // Show the main form
        this.mainForm.setVisible(true);
    }

    /**
     * Add all questions to the questions list.
     */
    public void addQuestions() {
        // Clear the list of questions
        this.questions.clear();

        // Add the first question
        this.questions.add(new Question(
                "<b>Welk volgende types zijn geldig in Java?</b>",
                new String[] {
                        "int, string, Double, Boolean",
                        "boolean, int, String, double",
                        "String, Int, double, boolean",
                        "double, boolean, String, Int"
                },
                1));
        this.questions.add(new Question(
                "<b>Wat is de waarde van <i>a</i>?</b><br />" +
                        "<br />" +
                        StringUtils.indentHtmlSpaces(4) + "int a = (int) 19.6",
                new String[]{
                        "19",
                        "19.6",
                        "20",
                        "18"
                },
                0));
        this.questions.add(new Question(
                "<b>Wat komt hier uit?</b><br />" +
                        "<br />" +
                        StringUtils.indentHtmlSpaces(4) + "(int) (24.768 * 100) / 100",
                new String[]{
                        "25",
                        "24.76",
                        "24",
                        "24.7"
                },
                2));
        this.questions.add(new Question(
                "<b>Wat komt hier uit?</b><br />" +
                        "<br />" +
                        StringUtils.indentHtmlSpaces(4) + "42.5 % 2.1",
                new String[]{
                        "0.5",
                        "1.0",
                        "1.25",
                        "<i>Deze expressie geeft een error</i>"
                },
                0));
        this.questions.add(new Question(
                "<b>Wat komt hier uit?</b><br />" +
                        "<br />" +
                        StringUtils.indentHtmlSpaces(4) + "for(int i = 1; i < 10; i += 2) {<br />" +
                        StringUtils.indentHtmlSpaces(8) + "System.out.printf(\"%1d\", i);<br />" +
                        StringUtils.indentHtmlSpaces(4) + "}",
                new String[]{
                        "123456789",
                        "13579",
                        "2468",
                        "<i>Geen van de bovenstaande</i>"
                },
                1));
    }

    /**
     * Get the questions.
     *
     * @return The list of questions.
     */
    public List<Question> getQuestions() {
        return this.questions;
    }

    /**
     * Get a specific question by index.
     *
     * @param i The index of the question.
     * @return The question.
     */
    public Question getQuestionById(int i) {
        return this.questions.get(i);
    }

    /**
     * Get the current question index.
     *
     * @return The current question index.
     */
    public int getCurrentQuestionId() {
        return this.currentQuestion;
    }

    /**
     * Get the current question.
     *
     * @return Current question.
     */
    public Question getCurrentQuestion() {
        return getQuestionById(getCurrentQuestionId());
    }

    /**
     * Load and show the next question.
     */
    public void nextQuestion() {
        // Check whether the current question is the last
        if(currentQuestion < questions.size() - 1) {
            // Increase the current question index by one
            currentQuestion++;

            // Show the next question in the frame
            this.questionForm.setQuestion(getCurrentQuestion());

        } else {
            JOptionPane.showMessageDialog(questionForm, "Uw score is: " + getScore());
        }
    }

    /**
     * This method is called when a question is selected.
     *
     * @param i The answer index.
     */
    public void selectedAnswer(int i) {
        // Print the answer in the console
        System.out.println("Answered: " + getCurrentQuestion().getAnswer(i, true));

        // Increase the score if answer is correct
        if(getCurrentQuestion().isCorrectAnswerIndex(i)) {
            addScore();
        }

        System.out.println("Uw score is: " + getScore());
        // Go to next question
        nextQuestion();
    }
    public void showQuestionForm() {
    questionForm.setVisible(true);
    }
}